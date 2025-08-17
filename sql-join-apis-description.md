## Apis description

### Vehicle booking details api
Create a get api like

`/vehicle/{vehicleId}/booking-details which returns the following data for a vehicle if found`

```java
class VehicleBookingResponse {

	// here date are in timestamp string
	public static class BookingDetails {
		private String startDate;
		private String endDate;
	}

	private String model;
	private int manufactureYear;
	private VehicleStatus status;
	private String vehicleType;
	private List<BookingDetails> bookingList;
}
```

### Availability schedule
This api will take in the start time and end time and will list all the vehicles with their bookings in that time period

#### API - /availability-schedule/start-time/end-time

```java
class VehicleScheduleResponse {

	// here date are in timestamp string
	public static class BookingSchedule {
		private String startDate;
		private String endDate;
	}

	private String model;
	private int manufactureYear;
	private VehicleStatus status;
	private String vehicleType;
	private BookingSchedule bookingSchedule;
}

```
### Note - 
1. Your api must return a **list** of VehicleScheduleResponse, one for each of the schedules

### Top vehicle types

#### Api - /analytics/top-vehicles/start-date/end-date

#### Description
We need a get api which supports the following query parameters
1. start date
2. end date
3. cutoff

which returns a list of vehicle types in sorted order of total number of bookings across all their vehicles and in that as well we need the top `cutoff` number of vehicles which have the maximum number of bookings in their type;

```java
class VehiclePopularityAnalysis {
    public static class VehicleStats {
        private String modelName;
        private Integer totalBookings;
    }
    
    private String vehicleTypeName;
    private Integer totalVehicleBookings;
    private List<VehicleStats> topVehicles;
}
```

Return a list of VehiclePopularityAnalysis.