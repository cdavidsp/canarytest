package com.csosa.healiostest.sample


val forecastDaysResponse = """
{
  "location": {
    "name": "Madrid",
    "region": "Madrid",
    "country": "Spain",
    "lat": 40.42,
    "lon": -3.7,
    "tz_id": "Europe/Madrid",
    "localtime_epoch": 1646516822,
    "localtime": "2022-03-05 22:47"
  },
  "current": {
    "last_updated_epoch": 1646516700,
    "last_updated": "2022-03-05 22:45",
    "temp_c": 8.0,
    "temp_f": 46.4,
    "is_day": 0,
    "condition": {
      "text": "Overcast",
      "icon": "//cdn.weatherapi.com/weather/64x64/night/122.png",
      "code": 1009
    },
    "wind_mph": 3.8,
    "wind_kph": 6.1,
    "wind_degree": 360,
    "wind_dir": "N",
    "pressure_mb": 1014.0,
    "pressure_in": 29.94,
    "precip_mm": 0.0,
    "precip_in": 0.0,
    "humidity": 49,
    "cloud": 100,
    "feelslike_c": 6.9,
    "feelslike_f": 44.5,
    "vis_km": 10.0,
    "vis_miles": 6.0,
    "uv": 1.0,
    "gust_mph": 6.9,
    "gust_kph": 11.2
  },
  "forecast": {
    "forecastday": [
      {
        "date": "2022-03-05",
        "date_epoch": 1646438400,
        "day": {
          "maxtemp_c": 11.7,
          "maxtemp_f": 53.1,
          "mintemp_c": 3.5,
          "mintemp_f": 38.3,
          "avgtemp_c": 7.6,
          "avgtemp_f": 45.6,
          "maxwind_mph": 11.4,
          "maxwind_kph": 18.4,
          "totalprecip_mm": 0.0,
          "totalprecip_in": 0.0,
          "avgvis_km": 10.0,
          "avgvis_miles": 6.0,
          "avghumidity": 51.0,
          "daily_will_it_rain": 0,
          "daily_chance_of_rain": 0,
          "daily_will_it_snow": 0,
          "daily_chance_of_snow": 0,
          "condition": {
            "text": "Sunny",
            "icon": "//cdn.weatherapi.com/weather/64x64/day/113.png",
            "code": 1000
          },
          "uv": 4.0
        },
        "astro": {
          "sunrise": "07:43 AM",
          "sunset": "07:11 PM",
          "moonrise": "09:19 AM",
          "moonset": "10:18 PM",
          "moon_phase": "Waxing Crescent",
          "moon_illumination": "13"
        }
      },
      {
        "date": "2022-03-06",
        "date_epoch": 1646524800,
        "day": {
          "maxtemp_c": 12.2,
          "maxtemp_f": 54.0,
          "mintemp_c": 3.0,
          "mintemp_f": 37.4,
          "avgtemp_c": 7.4,
          "avgtemp_f": 45.3,
          "maxwind_mph": 14.8,
          "maxwind_kph": 23.8,
          "totalprecip_mm": 0.0,
          "totalprecip_in": 0.0,
          "avgvis_km": 10.0,
          "avgvis_miles": 6.0,
          "avghumidity": 51.0,
          "daily_will_it_rain": 0,
          "daily_chance_of_rain": 0,
          "daily_will_it_snow": 0,
          "daily_chance_of_snow": 0,
          "condition": {
            "text": "Sunny",
            "icon": "//cdn.weatherapi.com/weather/64x64/day/113.png",
            "code": 1000
          },
          "uv": 4.0
        },
        "astro": {
          "sunrise": "07:42 AM",
          "sunset": "07:12 PM",
          "moonrise": "09:43 AM",
          "moonset": "11:22 PM",
          "moon_phase": "Waxing Crescent",
          "moon_illumination": "20"
        }
      },
      {
        "date": "2022-03-07",
        "date_epoch": 1646611200,
        "day": {
          "maxtemp_c": 10.4,
          "maxtemp_f": 50.7,
          "mintemp_c": 5.1,
          "mintemp_f": 41.2,
          "avgtemp_c": 6.9,
          "avgtemp_f": 44.4,
          "maxwind_mph": 10.5,
          "maxwind_kph": 16.9,
          "totalprecip_mm": 6.4,
          "totalprecip_in": 0.25,
          "avgvis_km": 8.3,
          "avgvis_miles": 5.0,
          "avghumidity": 80.0,
          "daily_will_it_rain": 1,
          "daily_chance_of_rain": 89,
          "daily_will_it_snow": 0,
          "daily_chance_of_snow": 0,
          "condition": {
            "text": "Moderate rain",
            "icon": "//cdn.weatherapi.com/weather/64x64/day/302.png",
            "code": 1189
          },
          "uv": 2.0
        },
        "astro": {
          "sunrise": "07:40 AM",
          "sunset": "07:13 PM",
          "moonrise": "10:07 AM",
          "moonset": "No moonset",
          "moon_phase": "Waxing Crescent",
          "moon_illumination": "27"
        }
      }
    ]
  }
}

""".trimIndent()
