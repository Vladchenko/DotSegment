package ru.yanchenko.vlad.dotsegment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import ru.yanchenko.vlad.dotsegment.generics.Dot;
import ru.yanchenko.vlad.dotsegment.generics.Lines;
import ru.yanchenko.vlad.dotsegment.generics.Segment;
import ru.yanchenko.vlad.dotsegment.listeners.FrameKeyListener;
import ru.yanchenko.vlad.dotsegment.listeners.FrameMouseListener;
import ru.yanchenko.vlad.dotsegment.listeners.FrameMouseMotionListener;
import ru.yanchenko.vlad.dotsegment.listeners.FrameMouseWheelListener;

public class Repository {

    //<editor-fold defaultstate="collapsed" desc="System related fields">
//** Trigger used to check if an Alt key is pressed.
    private boolean keyAlt = false;
    //** Trigger used to check if a Ctrl key is pressed.
    private boolean keyCtrl = false;
    //** Trigger used to check if an Shift key is pressed.
    private boolean keyShift = false;
    //** Trigger used to check if a full screen is enabled.
    private boolean fullScreen = true;
    //** Trigger used to check if a window frame is to be seen.
    private boolean windowFrame = false;
    //** Fields used for a FPS representation
    private int fps;
    //** Following 2 fields are in charge of a size of a fps label in a screen
    private int lblFPSWidth = 60;
    private int lblFPSHeight = 15;
    //** Updating FPS every nth milliseconds
    private int fpsUpdateTimeOut = 200;     // in millisecs
    private Color clrFPSLabel = new Color(150, 150, 150);
    private JLabel lblFPS = new JLabel("FPS:");
    //** Values used for fps computation
    private long beginTime;
    private long endTime = (new Date()).getTime();
    //** Field used to find out a size of a screen
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //** Width of a screen
    private int screenWidth = (int) screenSize.getWidth();
    //** Height of a screen
    private int screenHeight = (int) screenSize.getHeight();
    //** Center of a plane (screen)
    private Point pntScreenCenter
            = new Point(screenWidth / 2, screenHeight / 2);    
    /**
     * When full screen is off, then following fields stand for a size of a
     * window
     */
    private int ScreenWidth = 1200;
    private int ScreenHeight = 800;
    private Color clrWindowBackground = new Color(0, 0, 0);
    private static Repository oRepository;
    private static frmDrawingBoard oFrmDrawingBoard;
    private static Logic oLogic;
    private static Drawing oDrawing;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="User related fields">
    /**
     * Put to this area a fields that has to do with a problem realised in a 
     * program itself.
     */
    //** Number of balls present in an array.
    private int ballsQuantity = 1000;

    /**
     * Timer that is in charge of computations done while balls are converging,
     * i.e. scatterred balls going towards the destination balls.
     */
    private Timer tmrRendering;

    //<editor-fold defaultstate="collapsed" desc="Strings that stand for a paths where an images of a render button reside">
    private String strImgStartInitial = "pics/RenderButton/Start_Initial.png";
    private String strImgStartHovered = "pics/RenderButton/Start_Hovered.png";
    private String strImgStartPressed = "pics/RenderButton/Start_Pressed.png";
    private String strImgPauseInitial = "pics/RenderButton/Pause_Initial.png";
    private String strImgPauseHovered = "pics/RenderButton/Pause_Hovered.png";
    private String strImgPausePressed = "pics/RenderButton/Pause_Pressed.png";
    private String strImgContinueInitial = "pics/RenderButton/Continue_Initial.png";
    private String strImgContinueHovered = "pics/RenderButton/Continue_Hovered.png";
    private String strImgContinuePressed = "pics/RenderButton/Continue_Pressed.png";
    private String strImgInitInitial = "pics/RenderButton/Init_Initial.png";
    private String strImgInitHovered = "pics/RenderButton/Init_Hovered.png";
    private String strImgInitPressed = "pics/RenderButton/Init_Pressed.png";
//</editor-fold>
    private Map<String, String> mapStrImages = new HashMap();
    //** In charge of an actual file that is to hold an image of a ball.
    private File fileImg;
    
    private Segment segment = new Segment(1000,400,800,600);
    private Lines lines = new Lines();
    private Dot dot = new Dot(300,300,false);
    //</editor-fold>

    //** Used to paint a canvas on.
    private class frmDrawingBoard extends JFrame { }

    private Repository() { }
    
    //** Retrieving an object of a Repository
    public static Repository getInstance() {

        if (oRepository == null) {

            oRepository = new Repository();
            Repository.oLogic = Logic.getInstance(oRepository);
            Repository.oDrawing = Drawing.getInstance(oRepository);
            Repository.oFrmDrawingBoard = oRepository.new frmDrawingBoard();

            oRepository.initializeData();
            
        }
        return oRepository;

    }

    //** Adding a listeneres to a frame
    private void addListeners(JFrame frame) {
        frame.addKeyListener(new FrameKeyListener());
        frame.addMouseListener(new FrameMouseListener());
        frame.addMouseMotionListener(new FrameMouseMotionListener());
        frame.addMouseWheelListener(new FrameMouseWheelListener());
    }

    //** Initializing a JFrame
    private void initializeFrame(JFrame frame) {
        frame.setSize(oRepository.getScreenWidth(),
                oRepository.getScreenHeight());
        frame.setLocationRelativeTo(null);
        frame.setContentPane(oRepository.getoDrawing());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (!oRepository.isWindowFrame()) {
            frame.setUndecorated(true);
        }
        frame.setLayout(null);
        oRepository.getLblFPS().setBounds(
                frame.getWidth() - oRepository.getLblFPSWidth(),
                5,
                oRepository.getLblFPSWidth(),
                oRepository.getLblFPSHeight());
        frame.add(oRepository.getLblFPS());
        oRepository.getLblFPS().setForeground(oRepository.getClrFPSLabel());
        frame.setBackground(oRepository.getClrWindowBackground());
        frame.setVisible(true);
        this.addListeners(oFrmDrawingBoard);
        frame.requestFocus();
	// pack(); method might be of use, to pack a compnents of a frame.
    }

    //** Initializing some data - images, frame, adding listeners.
    private void initializeData() {

        //** Put an initialization code here */
        if (oRepository.isFullScreen()) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            oRepository.pntScreenCenter.x = (int) 
                    screenSize.getWidth() / 2;
            oRepository.pntScreenCenter.y = (int) 
                    screenSize.getHeight() / 2;
            oRepository.setScreenWidth((int) screenSize.getWidth());
            oRepository.setScreenHeight((int) screenSize.getHeight());
        } else {
            oRepository.pntScreenCenter.x = oRepository.getScreenWidth() / 2;
            oRepository.pntScreenCenter.y = oRepository.getScreenHeight() / 2;
        }

        this.initializeFrame(oFrmDrawingBoard);

        //** Setting a background for a canvas (screen)
        oDrawing.setBackground(
                oRepository.getClrWindowBackground()
        );
        
        // Initializing a lines
        oRepository.getLines().computeDots(
                oRepository.getSegment().getDot1().getX(), 
                oRepository.getSegment().getDot1().getY(), 
                oRepository.getSegment().getDot2().getX(), 
                oRepository.getSegment().getDot2().getY(),
                oRepository.getDot().getX(),
                oRepository.getDot().getY() );

        oRepository.getLines().computePerpendicularDots(
                oRepository.getSegment().getDot1().getX(),
                oRepository.getSegment().getDot1().getY(),
                oRepository.getSegment().getDot2().getX(),
                oRepository.getSegment().getDot2().getY()
        );

        oRepository.getLines().computeDotToLine1Distance(
                oRepository.getLines().getPerpendicular().getDot1().getX(),
                oRepository.getLines().getPerpendicular().getDot1().getY(),
                oRepository.getLines().getPerpendicular().getDot2().getX(),
                oRepository.getLines().getPerpendicular().getDot2().getY()
        );

        oRepository.getLines().computeDotToLine2Distance(
                oRepository.getLines().getPerpendicular2().getDot1().getX(),
                oRepository.getLines().getPerpendicular2().getDot1().getY(),
                oRepository.getLines().getPerpendicular2().getDot2().getX(),
                oRepository.getLines().getPerpendicular2().getDot2().getY()
        );

        //** Hiding mouse cursor
//        oRepository.getoDrawing().setCursor(
//                    oRepository.getoDrawing().getToolkit().createCustomCursor(
//                            new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
//                            new Point(0, 0),
//                            "null"));        
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">

    public Segment getSegment() {
        return segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }

    public Lines getLines() {
        return lines;
    }

    public void setLines(Lines lines) {
        this.lines = lines;
    }

    public Dot getDot() {
        return dot;
    }

    public void setDot(Dot dot) {
        this.dot = dot;
    }

    public Timer getTmrRendering() {
        return tmrRendering;
    }

    public void setTmrRendering(Timer tmrRendering) {
        this.tmrRendering = tmrRendering;
    }

    public File getFileImg() {
        return fileImg;
    }

    public void setFileImg(File fileImg) {
        this.fileImg = fileImg;
    }

    public Logic getoLogic() {
        return oLogic;
    }

    public void setoLogic(Logic oLogic) {
        this.oLogic = oLogic;
    }

    public Drawing getoDrawing() {
        return oDrawing;
    }

    public void setoDrawing(Drawing oDrawing) {
        this.oDrawing = oDrawing;
    }

    public Point getPntScreenCenter() {
        return pntScreenCenter;
    }

    public void setPntScreenCenter(Point pntScreenCenter) {
        this.pntScreenCenter = pntScreenCenter;
    }

    public JLabel getLblFPS() {
        return lblFPS;
    }

    public void setLblFPS(JLabel lblFPS) {
        this.lblFPS = lblFPS;
    }

    public int getLblFPSWidth() {
        return lblFPSWidth;
    }

    public void setLblFPSWidth(int lblFPSWidth) {
        this.lblFPSWidth = lblFPSWidth;
    }

    public int getLblFPSHeight() {
        return lblFPSHeight;
    }

    public void setLblFPSHeight(int fpsLabelHeight) {
        this.lblFPSHeight = fpsLabelHeight;
    }

    public Color getClrFPSLabel() {
        return clrFPSLabel;
    }

    public void setClrFPSLabel(Color clrFPSLabel) {
        this.clrFPSLabel = clrFPSLabel;
    }

    public int getFpsUpdateTimeOut() {
        return fpsUpdateTimeOut;
    }

    public void setFpsUpdateTimeOut(int fpsUpdateTimeOut) {
        this.fpsUpdateTimeOut = fpsUpdateTimeOut;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public Color getClrWindowBackground() {
        return clrWindowBackground;
    }

    public void setClrWindowBackground(Color clrWindowBackground) {
        this.clrWindowBackground = clrWindowBackground;
    }

    public static Repository getRepository() {
        return oRepository;
    }

    public static void setRepository(Repository Repository) {
        Repository.oRepository = Repository;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void setFullScreen(boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    public boolean isWindowFrame() {
        return windowFrame;
    }

    public void setWindowFrame(boolean windowFrame) {
        this.windowFrame = windowFrame;
    }

    public int getScreenWidth() {
        return ScreenWidth;
    }

    public void setScreenWidth(int ScreenWidth) {
        this.ScreenWidth = ScreenWidth;
    }

    public int getScreenHeight() {
        return ScreenHeight;
    }

    public void setScreenHeight(int ScreenHeight) {
        this.ScreenHeight = ScreenHeight;
    }

    public frmDrawingBoard getoFrmDrawingBoard() {
        return Repository.oFrmDrawingBoard;
    }

    public void setoFrmDrawingBoard(frmDrawingBoard aoFrmDrawingBoard) {
        Repository.oFrmDrawingBoard = aoFrmDrawingBoard;
    }

    public boolean isKeyAlt() {
        return keyAlt;
    }

    public void setKeyAlt(boolean keyAlt) {
        this.keyAlt = keyAlt;
    }

    public boolean isKeyCtrl() {
        return keyCtrl;
    }

    public void setKeyCtrl(boolean keyCtrl) {
        this.keyCtrl = keyCtrl;
    }

    public boolean isKeyShift() {
        return keyShift;
    }

    public void setKeyShift(boolean keyShift) {
        this.keyShift = keyShift;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
   
//</editor-fold>    
}
