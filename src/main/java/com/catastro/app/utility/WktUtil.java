package com.catastro.app.utility;

import java.text.ParseException;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.io.WKTReader;
import org.locationtech.jts.io.geojson.GeoJsonWriter;


public class WktUtil {
	public static MultiPolygon crearPoligonoDesdeWKT(String wkt) throws  org.locationtech.jts.io.ParseException {
		
        WKTReader reader = new WKTReader();

        return  (MultiPolygon)reader.read(wkt);
    }
	
	public static String convertirMultiPolygonAGeoJson(MultiPolygon multiPolygon) {
		
	    GeoJsonWriter writer = new GeoJsonWriter();
	    String geoJson = writer.write(multiPolygon);
	    
	    return geoJson;
	}

}
