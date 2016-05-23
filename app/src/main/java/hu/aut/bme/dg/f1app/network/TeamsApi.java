package hu.aut.bme.dg.f1app.network;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;

import hu.aut.bme.dg.f1app.model.Team;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface TeamsApi {
  
  /**
   * Formula 1 teams
   * Lists Formula 1 teams with their most important data.
   * @param id Team ID
   * @return Call<List<Team>>
   */
  
  @GET("teams")
  Call<List<Team>> teamsGet(
    @Query("id") Long id
  );

  
  /**
   * Modifies a team
   * Modifies a Formula 1 team.
   * @param id Team ID
   * @param teamName Name of team
   * @param teamLeaderName Name of team
   * @param teamImage Image of team
   * @param firstDriverId ID of first driver
   * @param secondDriverId ID of second driver
   * @return Call<Void>
   */
  
  @PUT("teams")
  Call<Void> teamsPut(
    @Query("id") Long id, @Query("teamName") String teamName, @Query("teamLeaderName") String teamLeaderName, @Query("teamImage") byte[] teamImage, @Query("firstDriverId") Integer firstDriverId, @Query("secondDriverId") Integer secondDriverId
  );

  
  /**
   * Adds a team
   * Adds a Formula 1 team.
   * @param teamName Name of team
   * @param teamLeaderName Name of team
   * @param teamImage Image of team
   * @param firstDriverId ID of first driver
   * @param secondDriverId ID of second driver
   * @return Call<Void>
   */
  
  @POST("teams")
  Call<Void> teamsPost(
    @Query("teamName") String teamName, @Query("teamLeaderName") String teamLeaderName, @Query("teamImage") byte[] teamImage, @Query("firstDriverId") Integer firstDriverId, @Query("secondDriverId") Integer secondDriverId
  );

  
  /**
   * Deletes a team
   * Deletes a Formula 1 team.
   * @param id Team ID
   * @return Call<Void>
   */
  
  @DELETE("teams")
  Call<Void> teamsDelete(
    @Query("id") Long id
  );

  
}
