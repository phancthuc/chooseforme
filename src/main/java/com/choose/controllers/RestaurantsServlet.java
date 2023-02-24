package com.choose.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.choose.beans.Restaurant;

/**
 * Servlet implementation class RestaurantsServlet
 */
@WebServlet("/RestaurantsServlet")
public class RestaurantsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RestaurantsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

//		response.getWriter().append("Served at: ").append(request.getContextPath());

		Random rand = new Random();
		response.setContentType("text/html");
		List<Restaurant> restaurants = null;
		
		String location = URLEncoder.encode(request.getParameter("location"), "UTF-8");
		String category = URLEncoder.encode(request.getParameter("food-category"), "UTF-8");
		
		System.out.println("LOCATION: " + location);
		System.out.println("CATEGORY: " + category);

		if (location.equals("")) {
			// go to jsp page to display message for empty input
			request.getRequestDispatcher("/jsp/EmptyLocation.jsp").forward(request, response);
		} else {

			try {
				restaurants = getRestaurants(location, category);
				if (restaurants == null)
					request.getRequestDispatcher("/jsp/Error.jsp").forward(request, response);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int randRestaurantIndex = rand.nextInt(restaurants.size());

			Restaurant randRestaurant = restaurants.get(randRestaurantIndex);

			System.out.println("CHOSEN: " + randRestaurant.getName());

			request.setAttribute("randRestaurant", randRestaurant);

			request.setAttribute("restaurants", restaurants);
			
			request.getRequestDispatcher("/jsp/DisplayRestaurants.jsp").forward(request, response);
		}
		

	}

	private List<Restaurant> getRestaurants(String location, String category) throws IOException, InterruptedException, ParseException {
		// Yelp API

		List<Restaurant> listOfRestaurants = new ArrayList<>();
		System.out.println("https://api.yelp.com/v3/businesses/search?location="
				+ location + "&term=restaurants&categories="+category+"&sort_by=distance&limit=20");
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.yelp.com/v3/businesses/search?location="
						+ location + "&term=restaurants&categories="+category+"&sort_by=distance&limit=20"))
				.header("accept", "application/json")
				.header("Authorization",
						"Bearer UeEfJeXHxOUatPXE8LiXaQ3FjXceDE7LBW1iVUE41RX0KRkYvbDoGrs1hlE2EWynoyPW5Np7di4_Am2O2wvdzjKMlLDaxm8gKTHeNIlunD9rGwWMIlG4MViyEGnbY3Yx")
				.method("GET", HttpRequest.BodyPublishers.noBody()).build();
		HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(response.body());

		JSONArray businesses = (JSONArray) jsonObject.get("businesses");
		if(businesses == null) {
			return null;
		}

		for (int i = 0; i < businesses.size(); i++) {
			JSONObject currBusiness = (JSONObject) businesses.get(i);

			String name = currBusiness.get("name").toString();
			Restaurant restaurant = new Restaurant(name);

			listOfRestaurants.add(restaurant);
			System.out.println(name);
		}

		return listOfRestaurants;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
