package hu.aut.bme.dg.f1app.network;



import hu.aut.bme.dg.f1app.model.Driver;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;


import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DriversApi {
  
  /**
   * Formula 1 drivers
   * Lists Formula 1 drivers with their most important data.
   * @param id Driver ID
   * @return Call<List<Driver>>
   */
  
  @GET("drivers")
  Call<List<Driver>> driversGet(
    @Query("id") Long id
  );

  
  /**
   * Modifies a driver
   * Modifies a Formula 1 driver.
   * @param id Driver ID
   * @param driverName Name of driver
   * @param driverNumber Number of driver
   * @param driverAge Age of driver
   * @param driverImage Image of driver
   * @param driverTeamId Team ID of driver
   * @return Call<Void>
   */
  
  @PUT("drivers")
  Call<Void> driversPut(
    @Query("id") Long id, @Query("driverName") String driverName, @Query("driverNumber") Integer driverNumber, @Query("driverAge") Integer driverAge, @Query("driverImage") byte[] driverImage, @Query("driverTeamId") Integer driverTeamId
  );

  
  /**
   * Adds a driver
   * Adds a Formula 1 driver.
   * @param driverName Name of driver
   * @param driverNumber Number of driver
   * @param driverAge Age of driver
   * @param driverImage Image of driver
   * @param driverTeamId Team ID of driver
   * @return Call<Void>
   */
  
  @POST("drivers")
  Call<Void> driversPost(
    @Query("driverName") String driverName, @Query("driverNumber") Integer driverNumber, @Query("driverAge") Integer driverAge, @Query("driverImage") byte[] driverImage, @Query("driverTeamId") Integer driverTeamId
  );

  
  /**
   * Deletes a driver
   * Deletes a Formula 1 driver.
   * @param id Driver ID
   * @return Call<Void>
   */
  
  @DELETE("drivers")
  Call<Void> driversDelete(
    @Query("id") Long id
  );

  
}
