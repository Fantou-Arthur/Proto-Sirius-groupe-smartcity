package edu.ezip.ing1.pds.requests.affluence;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ezip.ing1.pds.business.dto.affluence.TreeViewData;
import edu.ezip.ing1.pds.client.commons.ClientRequest;
import edu.ezip.ing1.pds.client.commons.NetworkConfig;
import edu.ezip.ing1.pds.commons.Request;

import java.io.IOException;

public class GetTreeViewClientRequest extends ClientRequest<Object, TreeViewData> {

    public GetTreeViewClientRequest(NetworkConfig networkConfig, int myBirthDate, Request request, Object info, byte[] bytes) throws IOException {
        super(networkConfig, myBirthDate, request, info, bytes);
    }

    @Override
    public TreeViewData readResult(String body) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final TreeViewData treeitem = mapper.readValue(body, TreeViewData.class);
        return treeitem;
    }
}
