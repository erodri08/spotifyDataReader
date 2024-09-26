package cpt;

import java.io.*;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import javafx.application.Application;

/**
 *  This program reads Spotify data from a CSV file, sorts it by artist name, and provides a user interface where they can view their full listening activity, a chart of their most popular artists or search for instances of a song, artist or album. 
 * @author Ethan Rodrigues
 */

 public class Main{
    public static void main(String[] args) throws IOException {
        // read CSV file
        BufferedReader theFile = new BufferedReader(new FileReader("CPTSpotifyData.csv"));

        // declare variable
        String strLine;
    
        // create array
        SpotifyData[] SpotifyDataArray = new SpotifyData[0];
    
        // while loop to add information from file onto the array
        while ((strLine = theFile.readLine()) != null) {
            String[] data = strLine.split(",");
            if (data.length == 4) {
                String strArtistName = data[0];
                String strAlbumName = data[1];
                String strTrackName = data[2];
                String strDate = data[3];
    
                SpotifyData SpotifyData = new SpotifyData(strArtistName, strAlbumName, strTrackName, strDate);
                SpotifyDataArray = Arrays.copyOf(SpotifyDataArray, SpotifyDataArray.length + 1);
                SpotifyDataArray[SpotifyDataArray.length - 1] = SpotifyData;
            }
        }
    
        // Launches the Main display
        Application.launch(MainStage.class, args);
    }

 }

 

 