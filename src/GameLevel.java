import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The type GameLevel.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter score;
    private Counter numOfLives;
    private Paddle paddle;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * Instantiates a new Game level.
     *
     * @param levelInformation the level information
     * @param keyboard         the keyboard
     * @param runner           the runner
     * @param score            the score
     * @param numOfLives       the num of lives
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboard, AnimationRunner runner,
                     Counter score, Counter numOfLives) {
        this.runner = runner;
        this.keyboard = keyboard;
        this.levelInformation = levelInformation;
        this.score = score;
        this.numOfLives = numOfLives;
    }

    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * remove collidable.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove sprite.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize.
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        int guiW = 800, guiH = 600;
        this.blockCounter = new Counter(levelInformation.numberOfBlocksToRemove());
        this.ballCounter = new Counter(levelInformation.numberOfBalls());
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.addSprite(levelInformation.getBackground());
        PrintingHitListener printListener = new PrintingHitListener();
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);
        ScoreTrackingListener scoreTracking = new ScoreTrackingListener(this.score);
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        LivesIndicator livesIndicator = new LivesIndicator(this.numOfLives);
        livesIndicator.addToGame(this);
        LevelIndicator levelIndicator = new LevelIndicator(this.levelInformation.levelName());
        levelIndicator.addToGame(this);
        int height = 20;
        Rectangle borderUp = new Rectangle(new Point(0, 20), guiW, height);
        Block borderBlock = new Block(borderUp, Color.gray, "X");
        borderBlock.addToGame(this);
        Rectangle borderLeft = new Rectangle(new Point(0, height), height, guiH - height);
        borderBlock = new Block(borderLeft, Color.gray, "X");
        borderBlock.addToGame(this);
        Rectangle borderRight = new Rectangle(new Point(guiW - height, height), height, guiH - height);
        borderBlock = new Block(borderRight, Color.gray, "X");
        borderBlock.addToGame(this);
        Rectangle deathRegion = new Rectangle(new Point(height, guiH), guiW - (2 * height), height);
        borderBlock = new Block(deathRegion, Color.gray, "X");
        borderBlock.addHitListener(ballRemover);
        borderBlock.addToGame(this);
        for (Block b : levelInformation.blocks()) {
            b.addHitListener(printListener);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreTracking);
            b.addToGame(this);
        }
    }

    /**
     * Play one turn.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle(); // or a similar method
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // countdown before turn starts.
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
        this.paddle.removeFromGame(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }
        if (this.blockCounter.getValue() == 0 || this.ballCounter.getValue() == 0) {
            this.running = false;
            if (this.ballCounter.getValue() == 0) {
                this.numOfLives.decrease(1);
            } else {
                this.score.increase(100);
            }
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Create balls on top of paddle.
     */
    public void createBallsOnTopOfPaddle() {
        int x = 420 - (levelInformation.numberOfBalls() * 25), y = 500;
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(x, y, 5, Color.WHITE, environment);
            ball.frameBoundaries(new Point(0, 0), new Point(200, 500));
            ball.setVelocity(levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
            x += 50;
        }
        while (ballCounter.getValue() < levelInformation.numberOfBalls()) {
            ballCounter.increase(1);
        }
        Rectangle pRec = new Rectangle(new Point(400 - (levelInformation.paddleWidth() / 2), 560),
                levelInformation.paddleWidth(), 20);
        this.paddle = new Paddle(pRec, Color.ORANGE, this.keyboard, levelInformation.paddleSpeed());
        paddle.frameBoundaries(20, 780);
        this.paddle.addToGame(this);
    }

    /**
     * Left blocks int.
     *
     * @return the int
     */
    public int leftBlocks() {
        return this.blockCounter.getValue();
    }

    /**
     * Left lives int.
     *
     * @return the int
     */
    public int leftLives() {
        return this.numOfLives.getValue();
    }
}