
///////////////////////////////////////////////////////////////////////////////////////////////
// color-shapes-desktop: The desktop UI for on Color Lines Engine.
// Copyright (C) 2016-2022 the original author or authors.
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; version 2
// of the License only.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
///////////////////////////////////////////////////////////////////////////////////////////////

package org.nanoboot.colorshapes.desktop.view.windows;

import org.nanoboot.colorshapes.desktop.localisation.impl.DummyResourcePackImpl;
import org.nanoboot.colorshapes.desktop.window.ColorShapesAbstractWindow;
import org.nanoboot.colorshapes.desktop.view.windows.gamingwindow.Board;
import org.nanoboot.colorshapes.desktop.view.windows.gamingwindow.BoardContainer;
import org.nanoboot.colorshapes.desktop.view.windows.gamingwindow.Cell;
import org.nanoboot.colorshapes.desktop.view.windows.gamingwindow.TempCommandWindow;
import org.nanoboot.colorshapes.engine.core.misc.ColorShapesEngineException;
import org.nanoboot.colorshapes.engine.core.misc.Task;
import org.nanoboot.colorshapes.engine.entity.core.PlayerDto;
import org.nanoboot.colorshapes.engine.infrastructure.Connection;
import org.nanoboot.colorshapes.engine.infrastructure.GameSource;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import org.nanoboot.colorshapes.engine.localisation.api.ResourcePack;
import org.nanoboot.powerframework.view.View;
import org.nanoboot.powerframework.view.layouts.SLayout;
import org.nanoboot.powerframework.view.boxes.MessageBox;

import static java.lang.Thread.sleep;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import org.nanoboot.powerframework.view.window.WindowSizeMode;
import org.nanoboot.powerframework.view.window.controls.SLabel;

/**
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.0.0
 */
public class GameWindow extends ColorShapesAbstractWindow {

    private SLayout sLayout = new SLayout();
    private ColumnConstraints biggerColumnConstraints = new ColumnConstraints();
    private ColumnConstraints smallerColumnConstraints = new ColumnConstraints();

    private RowConstraints biggerRowConstraints = new RowConstraints();
    private RowConstraints smallerRowConstraints = new RowConstraints();

    private MenuBar menuBar1;
    private MenuBar menuBar2;

    Menu gameMenu;
    Menu optionsMenu;
    Menu helpMenu;

    MenuItem newWithCurrentCompositionMenuItem;
    MenuItem newWithCustomCompositionMenuItem;
    MenuItem saveMenuItem;
    MenuItem loadMenuItem;
    MenuItem logOutMenuItem;
    MenuItem exitMenuItem;

    MenuItem analysisMenuItem;
    MenuItem nextBallsMenuItem;
    MenuItem playerSettingsMenuItem;
    MenuItem screenModeMenuItem;

    MenuItem helpMenuItem;
    MenuItem aboutThisGameMenuItem;

    HBox outerHBox;

    SLabel myNick;
    SLabel myScore;
    SLabel recordNick;
    SLabel recordScore;

    VBox vBox;

    private Board board = null;
    private static BoardContainer boardContainer = null;

    private Board nextBoard = null;
    private static BoardContainer nextBoardContainer = null;

    private static final String FXPADDING0FXMARGIN0 = "-fx-padding:0;-fx-margin:0;";
    private volatile boolean gameEnd;

    private volatile boolean closed;

    private HBox hBox;

    private ResourcePack resourcePack;

    private GameSource gameSource;
    private boolean init;

    /**
     * Constructor
     */
    public GameWindow(GameSource gameSource) {
        super(gameSource);
        gameEnd = false;
        closed = false;

        PauseTransition wait = new PauseTransition(Duration.seconds(0.25));
        wait.setOnFinished(e -> {
            if (this.board != null) {
                this.board.updateSize();
            }
            wait.playFromStart();
        });
        wait.play();

        Task task = new Task("Check for game end", () -> {
            GameWindow gameWindow = this;
            boolean stop = false;
            while (!closed) {
                while (!gameEnd && !stop) {
                    if (closed) {

                        try {
                            sleep(5000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        stop = true;
                    }
                    try {
                        sleep(200);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (!closed) {
                    Platform.runLater(() -> {
//                        if (playerSessionService.getCurrentSession() != null) {
                        MessageBox.showBox(gameSource.getConnection().getPlayer().getNick(), resourcePack.getText(30001) + this.myScore.getText());
//                        }
                        playNewGame();
                    });
                    gameEnd = false;
                }
            }
        });
        playNewGame();
        this.applicationArea.setOnKeyPressed(this::handleKeyPressed);
        this.applicationArea.requestFocus();
        this.updateBoard(16, 16);
    }

    private void playNewGame() {
    }

    public void setClosed() {
        closed = true;
    }

    /**
     *
     */
    @Override
    public void initAreaForUserInteraction(Object... args) {
        this.gameSource = (GameSource) args[0];

        this.resourcePack = DummyResourcePackImpl.getInstance();
        hBox = new HBox();

        this.setMinHeight(60 * View.getDpmm());
        this.setMinWidth(80 * View.getDpmm());
        sLayout = new SLayout();

        initMenu();
        this.setWindowTitle("Game");

        this.applicationArea.setAlignment(Pos.BOTTOM_CENTER);

        this.sLayout.setSpacing(false);
        HBox innerHBox = new HBox(menuBar2);
        outerHBox = new HBox(menuBar1, innerHBox);
        innerHBox.setAlignment(Pos.CENTER_RIGHT);
        outerHBox.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(innerHBox, Priority.ALWAYS);

        this.sLayout.add(outerHBox, 0, 0, 2, 1);

        outerHBox.setMinWidth(this.getWidth());

        biggerColumnConstraints = new ColumnConstraints();
        smallerColumnConstraints = new ColumnConstraints();

        biggerRowConstraints = new RowConstraints();
        smallerRowConstraints = new RowConstraints();

        this.biggerColumnConstraints.setPercentWidth(70);
        this.smallerColumnConstraints.setPercentWidth(15);
        this.biggerRowConstraints.setPercentHeight(90);
        this.smallerRowConstraints.setPercentHeight(10);

        if (gameSource == null) {
            throw new ColorShapesEngineException("gameSource==null");
        }
        Connection connection = gameSource.getConnection();
        PlayerDto player = connection.getPlayer();
        String nick = player.getNick();
        myNick = new SLabel(nick);
        myScore = new SLabel("0");
        recordNick = new SLabel("");
        recordScore = new SLabel("");

        myScore.setStyle("fx-background-color:red;");

        this.sLayout.getRowConstraints().addAll(
                this.smallerRowConstraints,
                this.biggerRowConstraints
        );

        this.sLayout.getColumnConstraints().addAll(
                this.smallerColumnConstraints,
                this.biggerColumnConstraints,
                this.smallerColumnConstraints
        );
        vBox = new VBox(this.outerHBox, this.sLayout);
        this.applicationArea.getChildren().add(vBox);

        StackPane stackPaneNick = new StackPane(this.myNick);
        stackPaneNick.setAlignment(Pos.CENTER);
        StackPane stackPaneScore = new StackPane(this.myScore);
        stackPaneScore.setAlignment(Pos.CENTER);

        stackPaneScore.setStyle(FXPADDING0FXMARGIN0);
        myScore.setStyle(FXPADDING0FXMARGIN0);

        StackPane stackPaneRecordNick = new StackPane(this.recordNick);

        stackPaneRecordNick.setStyle(FXPADDING0FXMARGIN0);
        recordScore.setStyle(FXPADDING0FXMARGIN0);

        myScore.setScaleX(1.2);
        recordScore.setScaleX(1.2);

        stackPaneRecordNick.setAlignment(Pos.CENTER);
        StackPane stackPaneRRecordScore = new StackPane(this.recordScore);
        stackPaneRRecordScore.setAlignment(Pos.CENTER);

        this.nextBoardContainer = new BoardContainer();
        this.sLayout.addRow(0, stackPaneRRecordScore, nextBoardContainer, stackPaneScore);
        this.sLayout.setStyle("-fx-margin:-10,0,0,0;-fx-padding:-10,0,0,0;");

        this.myNick.setManaged(true);

        this.boardContainer = new BoardContainer();

        this.sLayout.addRow(1, stackPaneRecordNick, this.boardContainer, stackPaneNick);

        this.sLayout.setPrefSize(this.getWidth(), this.getHeight() * 2); // Default width and height
        this.sLayout.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

        this.onResizingWindow();
        this.init = true;
    }

    public boolean isInited() {
        return ((this.hBox != null) && (!this.hBox.getChildren().isEmpty()));
    }

    /**
     * @param nick
     */
    public void updatePlayerNick(String nick) {
        this.myNick.setText(nick);
    }

    /**
     * @param nick
     */
    public void updateRecordNick(String nick) {
        this.recordNick.setText(nick);
    }

    /**
     * @param score
     */
    public void updatePlayerScore(String score) {
        this.myScore.setText(score);
    }

    /**
     * @param score
     */
    public void updateRecordScore(String score) {
        this.recordScore.setText(score);
    }

    /**
     * @param width
     * @param height
     */
    public void updateBoard(int height, int width) {
        this.board = new Board(gameSource, height, width, this.boardContainer);

        this.boardContainer.setBoard(board);
        board.updateSize();
    }

    /**
     * @param width
     */
    public void updateNextBoard(int width) {

        this.nextBoard = new Board(gameSource, 1, width, this.nextBoardContainer);
        for (int i = 1; i <= width; i++) {
            this.nextBoard.getCell(1, i).setIgnoreClickActions(true);
        }

        this.nextBoardContainer.setBoard(nextBoard);
        nextBoard.updateSize();

        ColumnConstraints columnConstraints = new ColumnConstraints();
        RowConstraints rowConstraints = new RowConstraints();

        double cellDimension = 100 / width;
        columnConstraints.setPercentWidth(cellDimension);

        this.nextBoard.setMinWidth(nextBoardContainer.getHeight() * width);
        this.nextBoard.setMaxWidth(nextBoardContainer.getHeight() * width);

        rowConstraints.setPercentHeight(100);

        nextBoard.getColumnConstraints().clear();
        nextBoard.getRowConstraints().clear();

        ColumnConstraints zeroColumnConstraints = new ColumnConstraints();
        zeroColumnConstraints.setPercentWidth(0);

        RowConstraints zeroRowConstraints = new RowConstraints();
        zeroRowConstraints.setPercentHeight(0);

        nextBoard.getColumnConstraints().add(zeroColumnConstraints);
        for (int i = 1; i <= width; i++) {
            nextBoard.getColumnConstraints().add(columnConstraints);
        }

        nextBoard.getRowConstraints().add(zeroRowConstraints);
        nextBoard.getRowConstraints().add(rowConstraints);
        if (!isInited()) {
            this.hBox.getChildren().add(new HBox());
        }
    }

    /**
     * Hides the menu.
     */
    public void hideMenu() {
        this.vBox.getChildren().remove(outerHBox);
    }

    /**
     * Shows the menu.
     */
    public void showMenu() {
        this.vBox.getChildren().add(outerHBox);
        outerHBox.toBack();
    }

    private void initMenu() {
        menuBar1 = new MenuBar();
        menuBar2 = new MenuBar();

        menuBar1.getStylesheets().addAll("styles/menu/menu.css", "styles/menu/menu" + View.getDefaultWindowColourSkin().getEnumColour().getNumber() + ".css");
        menuBar2.getStylesheets().addAll("styles/menu/menu.css", "styles/menu/menu" + View.getDefaultWindowColourSkin().getEnumColour().getNumber() + ".css");

        gameMenu = new Menu(resourcePack.getText(1001));
        optionsMenu = new Menu(resourcePack.getText(1101));
        helpMenu = new Menu(resourcePack.getText(1201));

        menuBar1.getMenus().addAll(gameMenu, optionsMenu);
        menuBar2.getMenus().addAll(helpMenu);

        newWithCurrentCompositionMenuItem = new MenuItem(resourcePack.getText(1011));
        newWithCustomCompositionMenuItem = new MenuItem(resourcePack.getText(1012));
        saveMenuItem = new MenuItem(resourcePack.getText(1013));
        loadMenuItem = new MenuItem(resourcePack.getText(1014));
        SeparatorMenuItem gameSeparatorMenuItem = new SeparatorMenuItem();
        logOutMenuItem = new MenuItem(resourcePack.getText(1021));
        exitMenuItem = new MenuItem(resourcePack.getText(1022));

        gameMenu.getItems().addAll(newWithCurrentCompositionMenuItem, newWithCustomCompositionMenuItem, gameSeparatorMenuItem, logOutMenuItem, exitMenuItem);

        analysisMenuItem = new MenuItem(resourcePack.getText(1111));
        nextBallsMenuItem = new MenuItem(resourcePack.getText(1113));
        playerSettingsMenuItem = new MenuItem(resourcePack.getText(1115));
        SeparatorMenuItem optionsSeparatorMenuItem = new SeparatorMenuItem();
        screenModeMenuItem = new MenuItem(resourcePack.getText(1121));

        optionsMenu.getItems().addAll(analysisMenuItem, nextBallsMenuItem, playerSettingsMenuItem, optionsSeparatorMenuItem, screenModeMenuItem);

        helpMenuItem = new MenuItem(resourcePack.getText(1201));
        SeparatorMenuItem helpSeparatorMenuItem = new SeparatorMenuItem();
        aboutThisGameMenuItem = new MenuItem(resourcePack.getText(1211));

        helpMenu.getItems().addAll(helpMenuItem, helpSeparatorMenuItem, aboutThisGameMenuItem);

        initHandlingMenuEvents();

    }

    private void initHandlingMenuEvents() {
        this.newWithCurrentCompositionMenuItem.setOnAction(this::handleNewWithCurrentSettingsMenuItemAction);
        newWithCustomCompositionMenuItem.setOnAction(e -> this.handleNewWithCustomSettingsMenuItemAction(e));
        logOutMenuItem.setOnAction(e -> this.handleLogOutMenuItemAction(e));
        exitMenuItem.setOnAction(e -> this.handleExitMenuItemAction(e));

        nextBallsMenuItem.setOnAction(e -> this.handleHideShowNextBallsMenuItemAction(e));

        playerSettingsMenuItem.setOnAction(e -> this.handlePlayerSettingsMenuItemAction(e));
        screenModeMenuItem.setOnAction(e -> this.handleSwitchToFullScreenModeWindowModeMenuItemAction(e));

        helpMenuItem.setOnAction(e -> this.handleHelpMenuItemAction(e));
        aboutThisGameMenuItem.setOnAction(e -> this.handleAboutThisGameMenuItemAction(e));

    }

    private void handleNewWithCurrentSettingsMenuItemAction(ActionEvent event) {
        startNewGameWithCurrentGameComposition();
    }

    /**
     *
     */
    public void startNewGameWithCurrentGameComposition() {
        playNewGame();
    }

    private void handleNewWithCustomSettingsMenuItemAction(ActionEvent event) {
        startNewWithCustomGameComposition();
    }

    /**
     *
     */
    public void startNewWithCustomGameComposition() {
        //todo
//        CustomCompositionWindow customCompositionWindow = new CustomCompositionWindow(gameCompositionService.getGameComposition());
//        customCompositionWindow.show();
    }

    private void handleSaveMenuItemAction(ActionEvent event) {
        this.close();
//        playerSessionService.endSession();
    }

    private void handleLoadMenuItemAction(ActionEvent event) {
        this.close();
//        playerSessionService.endSession();
    }

    private void handleLogOutMenuItemAction(ActionEvent event) {
        closed = true;
//        playerSessionService.endSession();
    }

    private void handleExitMenuItemAction(ActionEvent event) {
        closed = true;
        this.close();
//        applicationService.exit();
    }

    private void handleAnalysisMenuItemAction(ActionEvent event) {
    }

    private void handleHideShowNextBallsMenuItemAction(ActionEvent event) {

    }

    private void handlePlayerSettingsMenuItemAction(ActionEvent event) {
        //todo
//        PlayerSettingsWindow playerSettingsWindow = new PlayerSettingsWindow();
//        playerSettingsWindow.show();
    }

    private void handleSwitchToFullScreenModeWindowModeMenuItemAction(ActionEvent event) {
        switchToFullScreenModeWindowMode();
    }

    private void switchToFullScreenModeWindowMode() {
        if (this.getWindowSizeMode() == WindowSizeMode.FULLSCREEN) {
            this.switchToRestored();
            this.screenModeMenuItem.setText(resourcePack.getText(1121));
            if (this.board != null) {
                this.board.updateSize();
            }
        } else {
            this.switchToFullScreen();
            this.screenModeMenuItem.setText(resourcePack.getText(1122));
            {
                if (this.board != null) {
                    this.board.updateSize();
                }
            }

        }
    }

    private void handleHelpMenuItemAction(ActionEvent event) {
        HelpWindow helpWindow = new HelpWindow();
        helpWindow.show();
    }

    private void handleAboutThisGameMenuItemAction(ActionEvent event) {
        AboutThisGameWindow aboutThisGameWindow = new AboutThisGameWindow();
        aboutThisGameWindow.show();
    }

    private void handleSize(ActionEvent event) {
        MessageBox.showBox(null, null);
    }

    private void handleKeyPressed(KeyEvent e) {
        if (e.getEventType() != KEY_PRESSED) {
            e.consume();
            return;
        }

        KeyCode keyCode = e.getCode();

// Show the help window when the F1 key is pressed
        switch (keyCode) {
            case F4:
                startNewGameWithCurrentGameComposition();
                break;
            case F6:
                startNewWithCustomGameComposition();
                break;
            case F11:
                switchToFullScreenModeWindowMode();
                break;
            case F12:
                TempCommandWindow tempCommandWindow = new TempCommandWindow(gameSource);
                tempCommandWindow.show();
                break;
            case F10:
                loggingIsOn = !loggingIsOn;
                if (loggingIsOn) {
                    //org.nanoboot.powerframework.init(ColorShapes.class, org.nanoboot.powerframework.util.logging.Level.ALL, Target.FILES, DigitalInformationUnit.MEGABYTE, 100, new TimeZone("Europe/Prague"));
//                    org.nanoboot.powerframework.log.Logger.setLogOkayClasses(true);
                }
                if (!loggingIsOn) {
                    //org.nanoboot.powerframework.init(ColorShapes.class, org.nanoboot.powerframework.util.logging.Level.OFF, Target.NOTARGET, DigitalInformationUnit.MEGABYTE, 100, new TimeZone("Europe/Prague"));
                    //org.nanoboot.powerframework.logging.Logger.setLogOkayClasses(true);
                }
                break;

            default:
        }

        //e.consume();
    }

    private static boolean loggingIsOn = false;

    /**
     *
     */
    @Override
    public void onClosingWindow() {
        this.closed = true;
//        applicationService.exit();
    }

    /**
     *
     */
    @Override
    public void onResizingWindow() {
        if (this.nextBoard != null) {
            this.nextBoard.updateSize();
        }
        if (GameWindow.nextBoardContainer != null) {
            GameWindow.nextBoardContainer.updateSize();
        }

        if (this.board != null) {
            this.board.updateSize();
        } else {
        }
        if (outerHBox != null) {
            outerHBox.setMinWidth(this.getWidth());
        }
        if (sLayout != null) {
            int sLayoutHeight = (int) (this.getHeight() - this.outerHBox.getHeight() - 4.5 * View.getDpmm());

            this.myScore.setFont(Font.font((double) sLayoutHeight / 20));
            this.recordScore.setFont(Font.font((double) sLayoutHeight / 20));

            myScore.setScaleX(1.4);
            recordScore.setScaleX(1.4);
            myScore.setScaleY(1.4);
            recordScore.setScaleY(1.4);
        }
        if (GameWindow.boardContainer != null) {
            GameWindow.boardContainer.updateSize();
        }

        this.applicationArea.setMaxWidth(this.getWidth());

        if (nextBoard != null) {
            this.nextBoard.setMinWidth(nextBoard.getCell(1, 1).getMinHeight() * nextBoard.getWidthCount());
            this.nextBoard.setMaxWidth(nextBoard.getHeight() * nextBoard.getWidthCount());
//this.nextBoard.minWidthProperty().bind(nextBoard.getCell(1,1).heightProperty().multiply(nextBoard.getWidthCount()));
        }
    }

    /**
     *
     */
    @Override
    public void onMovingWindow() {

    }

    /**
     * @param row
     * @param column
     * @return
     */
    public Cell getCell(int row, int column) {
        return this.board.getCell(row, column);
    }

    /**
     * @param column
     * @return
     */
    public Cell getCell(int column) {
        return this.nextBoard.getCell(1, column);
    }

    /**
     * @param row1
     * @param column1
     * @param row2
     * @param column2
     */
    public void moveFromTo(int row1, int column1, int row2, int column2) {
        this.board.moveFromTo(row1, column1, row2, column2);
    }

    public void gameEnd() {
        this.gameEnd = true;
    }
}
