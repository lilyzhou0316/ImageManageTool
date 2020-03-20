package imageManageTool;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.util.Callback;

public class ImagePane extends TreeTableView<Image> {
    // root node
    TreeItem rootItem = new TreeItem(new Image());

    // columns:name,resolution, size, format, color, date, camera, location
    TreeTableColumn<Image, Image> columns[] = new TreeTableColumn[8];

    public ImagePane() {
        // initial columns' setting
        initColumns();

        // 扁平化显示, 不显示根节点, 但必须要有根节点
        this.setRoot(rootItem);
        this.setShowRoot(false);
    }

    // clear
    public void clear() {
        rootItem.getChildren().clear();
    }

    // add a new image
    public void add(Image data) {
        TreeItem item = new TreeItem(data);
        rootItem.getChildren().add(item);
    }

    public void remove(int index) {
        rootItem.getChildren().remove(index);
    }

    public List<Image> get() {
        ArrayList<Image> results = new ArrayList<Image>();

        ObservableList<TreeItem<Image>> items = rootItem.getChildren();
        for (int i = 0; i < items.size(); i++) {
            TreeItem<Image> item = items.get(i);
            Image value = item.getValue();
            results.add(value);
        }

        return results;
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();

        // set column width
        double w = this.getWidth();
        double w0 = w * 0.3;
        double w1 = w * 0.1;
        double w2 = w * 0.1;
        double w3 = w * 0.1;
        double w4 = w * 0.1;
        double w5 = w * 0.1;
        double w6 = w * 0.1;
        double w7 = w * 0.1;

        columns[0].setPrefWidth(w0);
        columns[1].setPrefWidth(w1);
        columns[2].setPrefWidth(w2);
        columns[3].setPrefWidth(w3);
        columns[4].setPrefWidth(w4);
        columns[5].setPrefWidth(w5);
        columns[6].setPrefWidth(w6);
        columns[7].setPrefWidth(w7);
    }

    private void initColumns() {
        // add columns to treetablePane
        columns[0] = new TreeTableColumn("Name");
        columns[1] = new TreeTableColumn("Size");
        columns[2] = new TreeTableColumn("Format");
        columns[3] = new TreeTableColumn("Date");
        columns[4] = new TreeTableColumn("Resolution");
        columns[5] = new TreeTableColumn("Color");
        columns[6] = new TreeTableColumn("Camera");
        columns[7] = new TreeTableColumn("Location");
        this.getColumns().addAll(columns);

        // set CellValueFactory
        Callback cellValueFactory = new Callback() {
            @Override
            public Object call(Object param) {
                CellDataFeatures p = (CellDataFeatures) param;
                return p.getValue().valueProperty();
            }
        };
        for (int i = 0; i < columns.length; i++) {
            columns[i].setCellValueFactory(cellValueFactory);
        }

        // set CellFactory，define every column cell's content
        columns[0].setCellFactory((param) -> {
            return new MyTreeTableCell("c0");
        });
        columns[1].setCellFactory((param) -> {
            return new MyTreeTableCell("c1");
        });
        columns[2].setCellFactory((param) -> {
            return new MyTreeTableCell("c2");
        });
        columns[3].setCellFactory((param) -> {
            return new MyTreeTableCell("c3");
        });
        columns[4].setCellFactory((param) -> {
            return new MyTreeTableCell("c4");
        });
        columns[5].setCellFactory((param) -> {
            return new MyTreeTableCell("c5");
        });
        columns[6].setCellFactory((param) -> {
            return new MyTreeTableCell("c6");
        });
        columns[7].setCellFactory((param) -> {
            return new MyTreeTableCell("c7");
        });

    }

    // 单元格的显示
    static class MyTreeTableCell extends TreeTableCell<Image, Image> {
        String columnID;

        public MyTreeTableCell(String columnID) {
            this.columnID = columnID;
        }

        @Override
        protected void updateItem(Image item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                setGraphic(null);
                // get image's infomation
                if (columnID.equals("c0")) {
                    setText(String.valueOf(item.getName()));
                } else if (columnID.equals("c1")) {
                    setText(String.valueOf(item.size));
                } else if (columnID.equals("c2")) {
                    setText(String.valueOf(item.getType()));
                } else if (columnID.equals("c3")) {
                    setText(String.valueOf(item.date));
                } else if (columnID.equals("c4")) {
                    setText(String.valueOf(item.getResolution()));
                } else if (columnID.equals("c5")) {
                    setText(String.valueOf(item.size));
                } else if (columnID.equals("c6")) {
                    setText(String.valueOf(item.size));
                } else if (columnID.equals("c7")) {
                    setText(String.valueOf(item.size));
                }
            }
        }

    }
}
