package aoeiuv020;
import java.util.*;
import java.io.*;
import java.util.regex.*;
import java.nio.charset.*;
import com.google.gson.*;
import okio.*;
/**
 * @author AoEiuV020
 * @version 1.0, 2017/02/02
 */
public class Main{
	public static void main(String[] args)throws Exception{
		File fBaidu=new File("res/cphrase.ini");
		//User Dictionary Plus
		File fUdp=new File("res/User Dictionary Plus Backup.txt");
		Charset charsetBaidu=Charset.forName("utf-16le");
		Charset charsetUdp=Charset.forName("utf8");
		Buffer bBaidu=new Buffer();
		//bom,fffe,读取时要跳过，写入时要写在开头，
		ByteString bom=new Buffer()
			.writeByte(0xff)
			.writeByte(0xfe)
			.readByteString();
		bBaidu.writeAll(Okio.source(fBaidu));
		//跳过bom,
		if(bBaidu.snapshot(2).equals(bom))
			bBaidu.skip(2);
		String sBaidu=bBaidu.readString(charsetBaidu);
		String baiduime="(\\w*)=(\\d*),(.*)";
		Pattern p=Pattern.compile(baiduime);
		Matcher m=p.matcher(sBaidu);
		ArrUdp arrUdp=new ArrUdp();
		while(m.find()){
			String shortcut=m.group(1);
			String index=m.group(2);
			String word=m.group(3);
			Udp udp=new Udp(word,shortcut);
			arrUdp.add(udp);
		}
		Gson gson=new GsonBuilder()
			.serializeNulls()
			.setPrettyPrinting()
			.create();
		Buffer bUdp=new Buffer()
			.writeString(gson.toJson(arrUdp),charsetUdp);
		bUdp.readAll(Okio.sink(fUdp));
	}
}
class Udp{
	String word;
	String shortcut;
	String frequency="250";
	String locale=null;
	public Udp(String word,String shortcut){
		this.word=word;
		this.shortcut=shortcut;
	}
}
class ArrUdp extends LinkedList<Udp>{
}
