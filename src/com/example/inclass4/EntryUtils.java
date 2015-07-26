package com.example.inclass4;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Xml;

public class EntryUtils {
	static public class SAXParser extends DefaultHandler {
		private XmlEntry item = null;
		private ArrayList<XmlEntry> itemsList = null;
		private StringBuffer xmlBuffer;

		static ArrayList<XmlEntry> parseItems(InputStream xml) throws IOException, SAXException {
			SAXParser parser = new SAXParser();
			Xml.parse(xml, Xml.Encoding.UTF_8, parser);
			return parser.getItems();
		}

		public ArrayList<XmlEntry> getItems() {
			return this.itemsList;
		}

		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			this.xmlBuffer = new StringBuffer();
			itemsList = new ArrayList<XmlEntry>();
		}

		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, attributes);
			if (localName.equals("entry")) {
				this.item = new XmlEntry();
			}
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			// TODO Auto-generated method stub
			super.characters(ch, start, length);
			xmlBuffer.append(ch, start, length);
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			super.endElement(uri, localName, qName);

			//if (item != null) {
				if (localName.equals("im:image")) {
					this.item.setImageUrl(xmlBuffer.toString().trim());
				} else if (localName.equals("title")) {
					this.item.setAppName(xmlBuffer.toString().trim());
				} else if (localName.equals("im:price")) {
					this.item.setAppPrice( Float.parseFloat(xmlBuffer.toString().trim()) );
				} 
			//}
			xmlBuffer.setLength(0);
		}
	}
}
