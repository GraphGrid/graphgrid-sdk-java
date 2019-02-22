package com.graphgrid.files;

import com.graphgrid.core.GraphGridHttpClient;
import com.graphgrid.core.utils.HttpMethod;
import com.graphgrid.files.model.DeleteFileRequest;
import com.graphgrid.files.model.FileServiceStatusRequest;
import com.graphgrid.files.model.FileServiceStatusResponse;
import com.graphgrid.files.model.PersistFileNodeOnlyRequest;
import com.graphgrid.files.model.PersistFileNodeOnlyResponse;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class GraphGridFileServiceClient implements GraphGridFileService
{

    public PersistFileNodeOnlyResponse createFileNodeWithoutUploading( PersistFileNodeOnlyRequest request )
    {
        try
        {
            Map<String,String> h = new HashMap<String,String>();
            h.put( "Authorization", "Bearer d7c095ba-065f-4478-95bf-494e8a904e3a" );
            request.setEndpoint( new URL( "https://dev-api.graphgrid.com/1.0/billing/upload/createOnly" ) );
            request.setHeaders( h );
            return new GraphGridHttpClient().invoke( request, PersistFileNodeOnlyResponse.class, HttpMethod.GET );
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
        return new PersistFileNodeOnlyResponse();
    }

    public FileServiceStatusResponse status( FileServiceStatusRequest request )
    {
        return null;
    }

    public void deleteFile( DeleteFileRequest request )
    {

    }
}
