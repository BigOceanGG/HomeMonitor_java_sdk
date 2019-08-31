package ocean;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ocean.error.ApiError;
import ocean.entity.ApplicationRes;
import ocean.utils.HttpClient;
import ocean.utils.JsonHelper;

public class Application {

    private String nodeUrl;
    private Gson gson;
    private JsonParser parser;

    public Application(String nodeUrl) {
        this.nodeUrl = nodeUrl;
        gson = JsonHelper.getGsonInstance();
        parser = JsonHelper.getParserInstance();
    }

    public ApplicationRes findAppInfo(String id) throws IOException, ApiError {
        String url = String.format("%s/manager/app/findAppInfo?id=%s", nodeUrl, id);
        ApplicationRes resp = this.callChainAPI(url, ApplicationRes.class);
        return resp;
    }

    private <T> T callChainAPI(String url, Class<T> classType) throws IOException, ApiError {
        String json = HttpClient.get(url);
        return parseResponse(classType, json);
    }

    private <T> T callChainAPI(String url, String jsonData, Class<T> classType) throws IOException, ApiError {
        String json = HttpClient.post(url, jsonData);
        return parseResponse(classType, json);
    }

    private <T> T parseResponse(Class<T> classType, String json) throws ApiError {
        try {
            JsonElement jsonElement = parser.parse(json);
            if (jsonElement.isJsonObject()) {
                JsonObject jsonObj = jsonElement.getAsJsonObject();
                if (jsonObj.get("error") != null) {
                    throw ApiError.fromJson(json);
                } else {
                    return gson.fromJson(jsonElement, classType);
                }
            } else {
                return gson.fromJson(json, classType);
            }
        } catch (Exception ex) {
            throw ApiError.fromJson(json);
        }
    }
}
