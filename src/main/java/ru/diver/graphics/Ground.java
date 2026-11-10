package ru.diver.graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.net.URL;

public class Ground {

    private static final int SOURCE_TOP = 400;
    private final Image image;

    public Ground (String resourсePath){
        URL imageUrl = getClass().getResource(resourсePath);

        if (imageUrl == null) {
            throw new IllegalStateException(
                    "Не найден ресурс: " + resourсePath
            );
        }
        image = new Image(imageUrl.toExternalForm());

        if (image.isError()){
            throw new IllegalStateException(
                    "Не удалось загрузить изображения: " + resourсePath,
                    image.getException()
            );
        }
    }

    public void draw(
            GraphicsContext graphics,
            double width,
            double height,
            double surfaceY
    ){
        double drawingHeight = height - surfaceY;

        if (width <=0 || drawingHeight <=0) {
            return;
        }

        double sourceHeight = image.getHeight() - SOURCE_TOP;

        double tileWidth = image.getWidth()
                * drawingHeight / sourceHeight;

        for (double x = 0; x < width; x += tileWidth) {
            graphics.drawImage(
                    image,
                    0,
                    SOURCE_TOP,
                    image.getWidth(),
                    sourceHeight,
                    x,
                    surfaceY,
                    tileWidth,
                    drawingHeight
            );
        }
    }
}
