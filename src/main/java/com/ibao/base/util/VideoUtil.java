package com.ibao.base.util;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class VideoUtil {
	
	public static String getVid(String url){
		Pattern pattern = Pattern.compile("id_(\\w+).html");
		Matcher matcher = pattern.matcher(url);
		if(matcher.find()){
			String videoId2 = matcher.group();
			return videoId2.substring(videoId2.indexOf("_")+1, videoId2.indexOf("."));
		}
		return null;
	}
	
	public static Map<String, Integer> checkVideo(String url){
		url = "http://v.youku.com/v_vpactionInfo/id/"+getVid(url)+"/pm/1";
		Map<String, Integer> map = new HashMap<>();
		try {
			Document document = Jsoup.connect(url).
					timeout(10*1000).
					userAgent("Mozilla/5.0 (Windows NT 6.1; rv:35.0) Gecko/20100101 Firefox/35.0")
					.get();
			Element e = document.select("#videodetailInfo").first();
			Elements elements = e.select("li");
			for(Element element : elements){
				String text = element.text();
				if(text.contains("总播放数:")){
					String value = text.replace("总播放数:", "").replace(",", "");
					map.put("vv", Integer.valueOf(value));
				}
				if(text.contains("顶 / 踩:")){
					String value = text.replace("顶 / 踩:", "").replace(" ", "").replace(",", "");
					String[] ss = value.split("/");
					map.put("up", Integer.valueOf(ss[0]));
					map.put("down", Integer.valueOf(ss[1]));
				}
				if(text.contains("收藏:")){
					String value = text.replace("收藏:", "").replace(",", "");
					map.put("subscribe", Integer.valueOf(value));
				}
				if(text.contains("评论:")){
					String value = text.replace("评论:", "").replace(",", "");
					map.put("comment", Integer.valueOf(value));
				}
			}
			return map;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public static Map<String, Object> getVideoInfo(String url){
		try {
			Document doc = Jsoup.connect(url).get();
			if(null != doc){
				Elements elements = doc.select("script");
				if(elements != null && elements.size() > 0){
					Element element = elements.get(5);
					String text = element.html();
					Map<String, Object> map = null;
					if(StringUtils.isNotBlank(text)){
						map = new HashMap<>();
						for(String s : text.split(";")){
							if(s.contains("var videoId = ")){
								String videoId = s.substring(s.indexOf("'")+1, s.lastIndexOf("'"));
								map.put("videoId", videoId);
							}
							if(s.contains("var showid=")){
								String showId = s.substring(s.indexOf("\"")+1, s.lastIndexOf("\""));
								map.put("showId", Integer.valueOf(showId));
							}
							if(s.contains("var playmode")){
								String playmode = s.substring(s.indexOf("\"")+1, s.lastIndexOf("\""));
								map.put("playmode", Integer.valueOf(playmode));
							}
							if(s.contains("var videoId2")){
								String videoId2 = s.substring(s.indexOf("'")+1, s.lastIndexOf("'"));
								map.put("videoId2", videoId2);
							}
						}
					}
					element = elements.get(15);
					text = element.html();
					if(StringUtils.isNotBlank(text)){
						if(null == map)
							map = new HashMap<>();
						for(String s : text.split(";")){
							if(s.contains("var catId=")){
								String catId = s.substring(s.indexOf("\"")+1, s.lastIndexOf("\""));
								map.put("catId", Integer.valueOf(catId));
							}
						}
					}
					return map;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		checkVideo("http://v.youku.com/v_show/id_XODU3MTMyNjYw.html");
		long t2 = System.currentTimeMillis();
		System.out.println(t2-t1);
	}

	
}
