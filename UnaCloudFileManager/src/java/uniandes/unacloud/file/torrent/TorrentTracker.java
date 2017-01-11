package uniandes.unacloud.file.torrent;

import java.io.File;
import java.io.FilenameFilter;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;
import com.turn.ttorrent.tracker.TrackedTorrent;
import com.turn.ttorrent.tracker.Tracker;

public class TorrentTracker{
	
	public static final  String TORRENT_EXTENSION = ".torrent";

	private FilenameFilter filter;
	private Tracker tracker;
	private boolean running;
	
	public static TorrentTracker instance;
	
	public static TorrentTracker getInstance(){
		if(instance==null)instance = new TorrentTracker();
		return instance;
	}
	
	private TorrentTracker(){		
		running = false;		
		filter = new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(TORRENT_EXTENSION);
			}
		};		

	}
	
	public void start(int port) throws Exception{
		if(running)throw new Exception("Service is currently running");
		tracker = new Tracker(new InetSocketAddress(port));
		running = true;
		new Thread(){
			@Override
			public void run() {
				System.out.println("Starting tracker with {} announced torrents..."+
						tracker.getTrackedTorrents().size());
				tracker.start();
			}
		}.start();		
	}
	
	public void announceTorrents(String path) throws Exception{
		if(!running)throw new Exception("Service must be running");
		try {			
			File parent = new File(path);
			for (File f : parent.listFiles(filter)) {
				System.out.println("Loading torrent from " + f.getName());
				tracker.announce(TrackedTorrent.load(f));
			}			
			System.out.println("Tracker is announced torrents..."+
					tracker.getTrackedTorrents().size());
		} catch (Exception e) {
			System.out.println("{}"+ e.getMessage()+"y .."+ e);
			e.printStackTrace();
		}
	}
	
	public void announceTorrent(String filePath) throws Exception{
		if(!running)throw new Exception("Service must be running");
		if(!filePath.endsWith(TORRENT_EXTENSION)) throw new Exception("Unsupported extension");
		try {			
			File file = new File(filePath);
			tracker.announce(TrackedTorrent.load(file));	
			System.out.println("Tracker is announced torrents..."+
					tracker.getTrackedTorrents().size());
		} catch (Exception e) {
			System.out.println("{}"+ e.getMessage()+"y .."+ e);
			e.printStackTrace();
		}
	}
	
	public void removeTorrent(String filePath)throws Exception{
		if(!running)throw new Exception("Service must be running");
		try {			
			File file = new File(filePath);
			tracker.remove(TrackedTorrent.load(file));			
			System.out.println("Tracker is announced torrents..."+
					tracker.getTrackedTorrents().size());
		} catch (Exception e) {
			System.out.println("{}"+ e.getMessage()+"y .."+ e);
			e.printStackTrace();
		}
	}
	
	public static void shareFile(File torrent, File zip){
		try {
			InetAddress ip = InetAddress.getByName("157.253.195.22");
			System.out.println("IP: "+ip);

			//Metodo del cliente se encarga de obtener el archivo original completo de un Torrent
			Client client = new Client(ip, 
					SharedTorrent.fromFile(torrent,zip.getParentFile()));

			// Establecer limites de subida y bajada de informacion en KB/seg
			client.setMaxDownloadRate(500000.0);
			client.setMaxUploadRate(500000.0);
			// Metodo para compartir los torrent locales del cliente, tasa medida en segundos. 
			client.share(3600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
