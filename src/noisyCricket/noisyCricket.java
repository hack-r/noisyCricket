/*
 *  Copyright 2016, Jason D. Miller
 *  All Rights Reserved
 */
package noisyCricket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jason
 */
public class noisyCricket {
    static Timer timer = new Timer();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {        
        new Task().run();

    }
    public static String[] generateRandomWords(int numberOfWords){
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for(int i = 0; i < numberOfWords; i++)
        {
            char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for(int j = 0; j < word.length; j++)
            {
                word[j] = (char)('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    static class Task extends TimerTask {
        @Override
        public void run() {
            int delay = (5 + new Random().nextInt(5)) * 1000;
            timer.schedule(new Task(), delay);
            System.out.println(new Date());
            String[] r = new String[300];
            r = generateRandomWords(300); 

            for (String r1 : r) {
                try {
                    URLConnection connection = new URL("https://www.google.com/search?q=" + r1).openConnection();
                    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
                    connection.connect();
                    
                    BufferedReader br  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
                    
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line);
                    }
                    System.out.println(sb.toString());
                } catch (MalformedURLException ex) {
                    Logger.getLogger(noisyCricket.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(noisyCricket.class.getName()).log(Level.SEVERE, null, ex);
                }

        }
        }

    }    
}
