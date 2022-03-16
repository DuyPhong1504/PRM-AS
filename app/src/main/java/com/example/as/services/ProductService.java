package com.example.as.services;

import com.example.as.model.Shoe;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {

    String SHOES = "shoes";

    @GET(SHOES)
    Call<Shoe[]> getAllTrainees();

    @GET(SHOES + "/{id}")
    Call<Shoe> getTraineeById(@Path("id") Object id);

    @POST(SHOES)
    Call<Shoe> createTrainee(@Body Shoe trainee);

    @PUT(SHOES + "/{id}")
    Call<Shoe> updateTrainee(@Path("id") Object id, @Body Shoe trainee);

    @DELETE(SHOES + "/{id}")
    Call<Shoe> deleteTrainee(@Path("id") Object id);

}
