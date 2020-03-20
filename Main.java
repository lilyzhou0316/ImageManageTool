package imageManageTool;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.ArrayList;
import javafx.stage.FileChooser.ExtensionFilter;

public class Main extends Application {
    // main window
    Stage stage;
    ImagePane imgpane = new ImagePane();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub
        this.stage = primaryStage;
        try {

            BorderPane root = new BorderPane();
            root.setCenter(imgpane);

            Scene scene = new Scene(root, 600, 400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

            HBox h = new HBox();

            Button btnUpload = new Button("Upload");
            btnUpload.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event1) {
                    openFolder();
                }
            });

            Button btnDownload = new Button("Download");
            btnDownload.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event2) {
                    downLoad();
                }
            });

            h.getChildren().addAll(btnUpload, btnDownload);
            root.setBottom(h);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // open folder
    private void openFolder() {
        // DirectoryChooser chooser = new DirectoryChooser();
        // chooser.setTitle("open folder");
        // // fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files",
        // // "*.txt"));
        // File dir = chooser.showDialog(stage);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("open folder");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("image file", "*.jpg"));

        File selectedFile = fileChooser.showOpenDialog(stage);
        // dir is the object user select
        if (selectedFile != null) {
            uploadFiles(selectedFile);
        }
    }

    // add the image user select
    private void uploadFiles(File selectedFile) {
        String path = selectedFile.getName();
        String type = path.substring(path.lastIndexOf("."), path.length());
        // 验证选中的文件类型是否为图片类型，是的话将selectedFile转换成自定义的Image类型
        if (type == "jpg") {
            // 转换dir类型to image
            Image object = new Image(selectedFile);
            // add the image
            imgpane.add(object);
        }

    }

    private void downLoad() {
        // 检查是否有图片被选中

        // 有则打开文件夹选择保存路径
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("save image");
        fileChooser.getExtensionFilters().add(new ExtensionFilter("image file", "*.jpg"));
        // 其它文件类型
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            downloadFiles(selectedFile);
        }
    }

    // download the image user select
    private void downloadFiles(File selectedFile) {

    }

    // 文件类型转换，用上下文菜单栏，邮件点击图片，弹出类型转换选择
    // https://imagemagick.org/index.php

    public static void main(String[] args) {
        launch(args);
    }
}
// }
