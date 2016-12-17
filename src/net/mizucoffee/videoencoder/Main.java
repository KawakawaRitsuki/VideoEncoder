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

                try {
                    System.out.println("エンコード開始");
                    System.out.println("ファイル名:" + files[0].getName());
                    Runtime.getRuntime().exec("move "+files[0].getPath() + " " + files[0].getParent()+ "¥0.flv");
                    ProcessBuilder pb = new ProcessBuilder("ffmpeg" ,"-i", files[0].getParent()+ "¥0.flv" , files[0].getName().split("¥¥.") + ".mp4");
                    pb.directory(new File(folder2.getPath()));
                    pb.inheritIO();
                    System.out.println("コマンド:" + pb.command());
                    System.out.println("カレントディレクトリ:" + pb.directory().getPath());
                    Process p = pb.start();

                    p.waitFor();
                    p.destroy();
                    System.out.println("エンコード完了");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                files[0].delete();
                System.out.println("ファイル削除完了");
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
