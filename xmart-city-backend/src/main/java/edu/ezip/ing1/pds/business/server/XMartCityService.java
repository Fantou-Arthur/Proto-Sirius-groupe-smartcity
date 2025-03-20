package edu.ezip.ing1.pds.business.server;

import edu.ezip.ing1.pds.business.server.capteur.CapteurService;
import edu.ezip.ing1.pds.business.server.place.PlaceService;
import edu.ezip.ing1.pds.business.server.affluence.AffluenceService;
import edu.ezip.ing1.pds.business.server.queries.Queries;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class XMartCityService {

    private final static String LoggingLabel = "B u s i n e s s - S e r v e r";
    private final Logger logger = LoggerFactory.getLogger(LoggingLabel);

    public static XMartCityService inst = null;
    public static final XMartCityService getInstance()  {
        if(inst == null) {
            inst = new XMartCityService();
        }
        return inst;
    }

    private XMartCityService() {

    }

    public final Response dispatch(final Request request, final Connection connection)
            throws InvocationTargetException, IllegalAccessException, SQLException, IOException {
        Response response = null;

        CapteurService capteurService = new CapteurService();
        PlaceService placeService = new PlaceService();
        AffluenceService affluenceService = new AffluenceService();

        final Queries queryEnum = Enum.valueOf(Queries.class, request.getRequestOrder());
        switch(queryEnum) {
            case INSERT_CAPTEUR:
                response = capteurService.InsertCapteur(request, connection);
            case SELECT_ALL_CAPTEURS:
                response = capteurService.SelectAllCapteurs(request, connection);
                break;
            case INSERT_AFFLUENCE:
                response = affluenceService.InsertAffluence(request, connection);
            case SELECT_ALL_PLACES:
                response = placeService.SelectAllPlaces(request, connection);
                break;
            case INSERT_PLACE:
                response = placeService.InsertPlace(request, connection);
                break;
            case UPDATE_PLACE:
                response = placeService.UpdatePlace(request, connection);
                break;
            case DELETE_PLACE:
                response = placeService.DeletePlace(request, connection);
                break;
            default:
                break;
        }
        return response;
    }



}
