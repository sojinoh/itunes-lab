package edu.macalester.comp124.music;

import acm.program.ConsoleProgram;

/**
 * Searches the "iTunes Music Library.xml" file for a query.
 */
public class MusicSearcher extends ConsoleProgram {

	public static final String PATH = "124-itunes/res/iTunes Music Library.xml";

	public void summarize(){
		ITunesReader ir = new ITunesReader(PATH);
		int summarizeCount = 0;
		while(true){
			Song result = ir.readNextSong();
			if(result==null){
				println(summarizeCount);
				break;
			}
			if(result.getCount()>=100){
				println("popular song: media: "+ir.readNextSong().toString());
			}
			summarizeCount++;
		}
			println("num songs: "+summarizeCount);
	}
	public void run() {
		summarize();
		while(true) {
			String words = readLine("Enter search query, or \"stop\" to stop: ");
			if (words.equals("stop")) {
				break;
			}
			search(words);
		}
	}

	public void search(String query){
		println("result for search \""+query+"\":");
		ITunesReader ir = new ITunesReader(PATH);
		double count = 0;
		while(true){
			Song result = ir.readNextSong();
			if(result==null){
				break;
			}
			if(result.matchesQuery(query)){
				println("matching song: media: "+result.toString());
			}
			count+=0.02;
		}
		println("search took "+count+" seconds");
	}
}
