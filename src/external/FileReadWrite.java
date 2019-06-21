package external;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

// http://javatutorialhq.com/java/io/bufferedreader-class-example/
import org.json.JSONArray;
import org.json.JSONObject;

public class FileReadWrite {
	public static void main(String[] args) throws Exception {
		String intputFile = "C:\\0Data\\Eclipse Java Data\\Leetcode Java\\src\\LyftAutoCompleteWord\\wordsInput.txt";
		String outputFile = "C:\\0Data\\Eclipse Java Data\\Leetcode Java\\src\\LyftAutoCompleteWord\\outputFileReadWrite.txt";
		//testReadWriteFile(intputFile, outputFile);
		//testReadWriteFile2(intputFile, outputFile);
		testURLStream();
	}
	
	
	public static void testReadWriteFile2(String intputFile, String outputFile) {
		BufferedWriter bw = null;
        FileWriter fw = null;

        try {

            String content = "This is the content to write into file\n";

            fw = new FileWriter("app.log");
            bw = new BufferedWriter(fw);
            bw.write(content);

        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        } finally {
            try {
                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                System.err.format("IOException: %s%n", ex);
            }
        }
	}
	public static void testReadWriteFile(String intputFile, String outputFile) {
		

		try {
			File fileOutput = new File(outputFile);
			fileOutput.createNewFile();
			BufferedReader reader = new BufferedReader(new FileReader(new File(intputFile)));
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutput));

			String readLine;
			while ((readLine = reader.readLine()) != null) {
				System.out.println("readLine: " + readLine);
				String[] parse = readLine.split(" ");
				String word = parse[0];
				int rank = Integer.valueOf(parse[1]);
				System.out.println("word: " + word + " rank: " + rank);

				writer.write("word: " + word + " rank: " + rank);
				writer.newLine();
			}
			reader.close();
			writer.close();

		} catch (IOException e) {
			new RuntimeException("Exception thrown while reading the input file", e);

		}
	}
	
	public static void testURLStream() throws Exception {


		try {
			String urlString = "https://jsonmock.hackerrank.com/api/movies/search/?Title=spiderman";
			String urlPage = "https://jsonmock.hackerrank.com/api/movies/search/?Title=spiderman&page=";

			// create the url
			URL url0 = new URL(urlString);
			BufferedReader reader0 = new BufferedReader(new InputStreamReader(url0.openStream()));
			String line;
			int pageNumber = 0;
			while ((line = reader0.readLine()) != null) {
				System.out.println("hello");
				System.out.println("line hello: " + line);
				JSONObject obj = new JSONObject(line);
				String pages = obj.get("total_pages").toString(); 
				//String pages = obj.getString("total_pages").toString(); // can't use getString(), it is a integer.
				pageNumber = Integer.parseInt(pages);
				// Integer a = (int) (obj.get("total_pages"));
				System.out.println("pageNumber: " + pageNumber);
				
			}
			reader0.close();
			
			
			// TicketMaster:
			// HttpURLConnection connection = (HttpURLConnection) new URL(URL + "?" + query).openConnection();
			// try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			
			int item = 1;
			for (int page = 1; page <= pageNumber; page++) {
				URL url = new URL(urlPage  + page);
				BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
					JSONObject obj = new JSONObject(line);
					System.out.println("line: " + line.getClass().getName());
					System.out.println("obj.getClass().getName(): " + obj.getClass().getName());
					JSONArray array = obj.getJSONArray("data");
					for (int i = 0; i < array.length(); i++) {
						JSONObject obj2 = array.getJSONObject(i);
						System.out.println("item: " + item + " obj2: " + obj2.get("Title"));
						item++;
					}
				}

				reader.close();
			}
		} catch(Exception e) {
			new RuntimeException("Exception thrown while reading the input file", e);
		}
		
	}
}
