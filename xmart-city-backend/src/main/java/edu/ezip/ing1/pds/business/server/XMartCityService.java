package edu.ezip.ing1.pds.business.server;

import edu.ezip.ing1.pds.business.server.address.AddressService;
import edu.ezip.ing1.pds.business.server.capteur.CapteurService;
import edu.ezip.ing1.pds.business.server.entity.EntityService;
import edu.ezip.ing1.pds.business.server.place.PlaceService;
import edu.ezip.ing1.pds.business.server.affluence.AffluenceService;
import edu.ezip.ing1.pds.business.server.queries.Queries;
import edu.ezip.ing1.pds.business.server.user.UserService;
import edu.ezip.ing1.pds.commons.Request;
import edu.ezip.ing1.pds.commons.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
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
            throws InvocationTargetException, IllegalAccessException, SQLException, IOException, NoSuchAlgorithmException {
        Response response = null;

        CapteurService capteurService = new CapteurService();
        PlaceService placeService = new PlaceService();
        AffluenceService affluenceService = new AffluenceService();
        UserService userService = new UserService();
        EntityService entityService = new EntityService();
        AddressService addressService = new AddressService();

        final Queries queryEnum = Enum.valueOf(Queries.class, request.getRequestOrder());
        switch(queryEnum) {
            case SELECT_ID_NAME_PLACES:
                response = placeService.SelectIdNamePlaces(request, connection);
                break;
            case EDIT_CAPTEUR:
                response = capteurService.EditCapteur(request, connection);
                break;
            case DELETE_CAPTEUR:
                response = capteurService.DeleteCapteur(request, connection);
                break;
            case INSERT_CAPTEUR:
                response = capteurService.InsertCapteur(request, connection);
                break;
            case SELECT_ALL_CAPTEURS:
                response = capteurService.SelectAllCapteurs(request, connection);
                break;
            case INSERT_AFFLUENCE:
                response = affluenceService.InsertAffluence(request, connection);
                break;
            case EDIT_AFFLUENCE:
                response = affluenceService.EditAffluence(request, connection);
                break;
            case DELETE_AFFLUENCE:
                response = affluenceService.DeleteAffluence(request, connection);
                break;
            case SELECT_ALL_AFFLUENCES:
                response = affluenceService.SelectAllAffluence(request, connection);
                break;
            case GET_TREE_VIEW:
                response = affluenceService.GetTreeView(request, connection);
                break;
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
            case SELECT_ALL_ENTITY:
                response = entityService.selectAllEntity(request, connection);
                break;
            case INSERT_USER:
                response = userService.InsertUser(request, connection);
                break;
            case LOGIN_USER:
                    response = userService.LoginUser(request, connection);
                break;
            case SELECT_ALL_ADDRESS:
                response = addressService.SelectAllAddress(request, connection);
                break;
            case INSERT_ADDRESS:
                response = addressService.InsertAddress(request, connection);
                break;
            case UPDATE_ADDRESS:
                response = addressService.UpdateAddress(request, connection);
                break;
            case DELETE_ADDRESS:
                response = addressService.DeleteAddress(request, connection);
                break;
            default:
                logger.warn("query type not present in XMartCityService cases !!!!!!!");
                break;
        }
        return response;
    }



}
