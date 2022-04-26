package run.config;

import company.entityclass.role.User;
import company.storage.dao.database.databasestorage.DBStorage;
import company.storage.dao.database.dao.DataBaseAlbumDao;
import company.storage.dao.database.dao.DataBaseArtistDao;
import company.storage.dao.database.dao.DataBaseSongDao;
import company.storage.dao.database.databasestorage.MyConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class Config implements WebMvcConfigurer {

    @Bean MyConnection connectionBean(){
        return  new MyConnection();
    }

    @Bean
    User userBean(){return new User();}

    @Bean
    public DataBaseArtistDao dataBaseArtistDaoBean(MyConnection myConnection){
        return new DataBaseArtistDao(myConnection);
    }

    @Bean
    public DataBaseAlbumDao dataBaseAlbumDaoBean(MyConnection myConnection){
        return new DataBaseAlbumDao(myConnection);
    }

    @Bean
    public DataBaseSongDao dataBaseSongDaoBean(MyConnection myConnection){
        return new DataBaseSongDao(myConnection);
    }

    @Bean
    public DBStorage dbStorageBean(){
        return DBStorage.getInstance();
    }

}
