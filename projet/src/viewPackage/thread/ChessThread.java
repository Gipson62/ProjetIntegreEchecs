package viewPackage.thread;

import viewPackage.PanelManager;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;

public class ChessThread extends Thread {
    ArrayList<BufferedImage> images;
    PanelManager panelManager;
    int currentIndex;
    public ChessThread(PanelManager panelManager) {
        this.panelManager = panelManager;
        this.currentIndex = 0;
        images = new ArrayList<>();
        for(int i = 1; i <= 83; i++) {
            if (i < 10) {
                try {
                    images.add(ImageIO.read(new File("projet/resources/images/frame-0"+i+".png")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                try {
                    images.add(ImageIO.read(new File("projet/resources/images/frame-"+i+".png")));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(this.images);
    }
    @Override
    public void run() {
        try {
            while(true) {
                if(this.images.size() - 1 > this.currentIndex){
                    this.currentIndex += 1;
                } else {
                    this.currentIndex = 0;
                }
                this.panelManager.right.setCurrImage(this.images.get(this.currentIndex));
                this.panelManager.right.repaint();
                Thread.sleep(1500);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
