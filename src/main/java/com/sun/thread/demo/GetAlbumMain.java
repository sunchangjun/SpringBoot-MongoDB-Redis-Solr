package com.sun.thread.demo;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.sun.mysql.entity.Album;
import com.sun.mysql.entity.QmSong;

@Service
public class GetAlbumMain {

//	@Autowired
//	QmSongTask qmSongTask;
	private static ExecutorService executor = Executors.newFixedThreadPool(5);

	public void getAlbum(Album album) throws Exception {
		List<QmSong> songs = album.getSonglist();
		List<QqSongCallable> tasks = new ArrayList<QqSongCallable>();
		for (QmSong song : songs) {
			QqSongCallable task = new QqSongCallable(song.getSongId());
			tasks.add(task);
		}
		List<Future<Long>> rets = executor.invokeAll(tasks);
		List<Long> songIds = new ArrayList<Long>();
		for (Future<Long> ret : rets) {
			Long songId = ret.get();
			if(songId!=null){
				songIds.add(songId);
			}
		}
		System.out.println(songIds.toString());
	}
	
	public static QmSong createSong(long song_id){
		QmSong s1 = new QmSong();
		s1.setSongId(song_id);
		return s1;
	}
	
	public static void main(String[] args) throws Exception {
		System.setProperty("spring.profiles.active", "develop");
//		EnvSupport.setEnv(EnvSupport.Env.develop);
		System.setProperty("reco-env", "develop");
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
		GetAlbumMain albumTask = (GetAlbumMain) context.getBean(GetAlbumMain.class);
		Album album = new Album();
		List<QmSong> ss = new ArrayList<QmSong>();
		album.setSonglist(ss);
		ss.add(createSong(215132366));
		ss.add(createSong(215081499));
		ss.add(createSong(215164687));
		ss.add(createSong(215427556));
		ss.add(createSong(215191293));
		ss.add(createSong(215436045));
		ss.add(createSong(215239869));
		ss.add(createSong(215063050));
		ss.add(createSong(214950775));
		ss.add(createSong(215079317));
		ss.add(createSong(214982811));
		ss.add(createSong(215166457));
		ss.add(createSong(215436038));
		ss.add(createSong(214920523));
		albumTask.getAlbum(album);
		executor.shutdown();
		context.close();
	}

}
