package net.mizucoffee.videoencoder;

import java.io.File;
import java.io.IOException;

public class Main {

    // java -jar XXX.jar 監視フォルダ 出力フォルダ

    public static void main(String[] args) {
        if(args.length != 2){
            System.err.println("ERROR: 引数が不正です");
            System.exit(1);
        }
        File folder1 = new File(args[0]);
        File folder2 = new File(args[1]);

        if(!folder1.exists()){
            System.err.println("ERROR: 引数1が存在しません");
            System.exit(1);
        }

        if(!folder2.exists()){
            System.err.println("ERROR: 引数2が存在しません");
            System.exit(1);
        }

        if(folder1.isFile()){
            System.err.println("ERROR: 引数1がディレクトリではありません");
            System.exit(1);
        }

        if(folder2.isFile()){
            System.err.println("ERROR: 引数2がディレクトリではありません");
            System.exit(1);
        }

        while(true){
            File[] files = folder1.listFiles();
            if(files.length != 0) {
                files[0].getPath();

                Runtime runtime = Runtime.getRuntime();
                try {
                    System.out.println("ffmpeg -i \"" + files[0].getPath() + "\" \"" + folder2 + "¥" + files[0].getName().split("¥¥.")[0] + ".mp4\"");
                    Process p = runtime.exec("ffmpeg -i \"" + files[0].getPath() + "\" \"" + folder2 + "¥" + files[0].getName().split("¥¥.")[0] + ".mp4\"");
                    p.waitFor();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                files[0].delete();
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
