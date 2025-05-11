package edu.ezip.ing1.pds.requests.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.place.Place;
import edu.ezip.ing1.pds.business.dto.user.User;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class SignUpClientRequest extends ClientRequest<User, User > {

    public SignUpClientRequest(
            NetworkConfig networkConfig, int myBirthDate, Request request, User info, byte[] bytes)
            throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public User readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final User user = mapper.readValue(body, User.class);
        return user;
    }

}
