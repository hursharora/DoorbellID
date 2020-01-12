package com.example.dooridentify.network;

import com.example.dooridentify.model.Profile;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    private HttpRequester httpRequester = new HttpRequester();
    private String requestURL = "https://immense-harbor-93861.herokuapp.com/profile";

    public List<Profile> getProfiles() throws IOException {
        String jsonReturn = httpRequester.makeHttpRequest(requestURL);
        JsonArray profileArr = new Gson().fromJson(jsonReturn, JsonArray.class);
        ArrayList<Profile> profiles = new ArrayList<>();
        for (int i = 0; i < profileArr.size(); i++) {
            JsonObject profile = profileArr.get(i).getAsJsonObject();
            String profName = profile.get("name").getAsString();
            String profDescription = profile.get("description").getAsString();
            String imgUrl = profile.get("URL").getAsString();
            profiles.add(new Profile(profName, profDescription, imgUrl));
        }
        return profiles;
    }



}
