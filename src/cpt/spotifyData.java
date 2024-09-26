package cpt;
import java.io.*;
import java.util.Arrays;

public class SpotifyData {
    
    // Initialize variables
    private String strArtistName;
    private String strAlbumName;
    private String strTrackName;
    private String strDate;

    public SpotifyData(String strArtistName, String strAlbumName, String strTrackName, String strDate) {
        this.strArtistName = strArtistName;
        this.strAlbumName = strAlbumName;
        this.strTrackName = strTrackName;
        this.strDate = strDate;
    }

    // Getters and setter Methods

    /**
    * returns the artist name
    * @return strArtistName  the name of the artist 
    * @author E. Rodrigues
    */
    public String getStrArtistName() {
        return strArtistName;
    }

    /**
    * sets the artist name
    * @param strArtistName  the name of the artist 
    * @author E. Rodrigues
    */
    public void setStrArtistName(String strArtistName) {
        this.strArtistName = strArtistName;
    }

    /**
    * gets the album name
    * @return strAlbumName  the name of the album 
    * @author E. Rodrigues
    */
    public String getStrAlbumName() {
        return strAlbumName;
    }

    /**
    * sets the artist name
    * @param strAlbumName  the name of the album 
    * @author E. Rodrigues
    */
    public void setStrAlbumName(String strAlbumName) {
        this.strAlbumName = strAlbumName;
    }

    /**
    * gets the track name
    * @return strTrackName  the name of the track 
    * @author E. Rodrigues
    */
    public String getStrTrackName() {
        return strTrackName;
    }

    /**
    * sets the track name
    * @param strTrackName  the name of the track 
    * @author E. Rodrigues
    */
    public void setStrTrackName(String strTrackName) {
        this.strTrackName = strTrackName;
    }

    /**
    * gets the Date listened to the track
    * @return strDate  the date 
    * @author E. Rodrigues
    */
    public String getStrDate() {
        return strDate;
    }

    /**
    * sets the track date
    * @param strDate  the date of the track 
    * @author E. Rodrigues
    */
    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }
}
