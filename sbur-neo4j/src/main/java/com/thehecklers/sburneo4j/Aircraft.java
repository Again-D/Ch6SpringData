package com.thehecklers.sburneo4j;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.Instant;
import java.util.Objects;

@Node
@JsonIgnoreProperties(ignoreUnknown=true)
public class Aircraft {

    // neo4j는 엔티티에 이미 고유식별자가 존재하더라도, 별개의 neo4j를 위한
    // 고유 식별자를 필요로 함

    // Lombok이 작동 안하는 경우 다 작성...
    @Id
    @GeneratedValue
    private Long neoId;

    private Long id;
    private String callsign, squawk, reg, flightno, route, type, category;
    private int altitude, heading, speed;

    @JsonProperty("vert_rate")
    private int vertRate;
    @JsonProperty("selected_altitude")
    private int selectedAltitude;
    private double lat, lon, barometer;

    @JsonProperty("polar_distance")
    private double polarDistance;
    @JsonProperty("polar_bearing")
    private double polarBearing;

    @JsonProperty("is_adsb")
    private boolean isAdsb;
    @JsonProperty("is_on_ground")
    private boolean isOnGround;

    @JsonProperty("last_seen_time")
    private Instant lastSeenTime;
    @JsonProperty("pos_update_time")
    private Instant posUpdateTime;
    @JsonProperty("bds40_seen_time")
    private Instant bds40SeenTime;

    // 기본 생성자
    public Aircraft() {}
    // 필드 생성자
    public Aircraft(Long id, String callsign, String squawk, String reg, String flightno,
                    String route, String type, String category,
                    int altitude, int heading, int speed,
                    int vertRate, int selectedAltitude,
                    double lat, double lon, double barometer,
                    double polarDistance, double polarBearing,
                    boolean isAdsb, boolean isOnGround,
                    Instant lastSeenTime, Instant posUpdateTime, Instant bds40SeenTime) {
        this.id = id;
        this.callsign = callsign;
        this.squawk = squawk;
        this.reg = reg;
        this.flightno = flightno;
        this.route = route;
        this.type = type;
        this.category = category;
        this.altitude = altitude;
        this.heading = heading;
        this.speed = speed;
        this.vertRate = vertRate;
        this.selectedAltitude = selectedAltitude;
        this.lat = lat;
        this.lon = lon;
        this.barometer = barometer;
        this.polarDistance = polarDistance;
        this.polarBearing = polarBearing;
        this.isAdsb = isAdsb;
        this.isOnGround = isOnGround;
        this.lastSeenTime = lastSeenTime;
        this.posUpdateTime = posUpdateTime;
        this.bds40SeenTime = bds40SeenTime;
    }

    public Long getNeoId() {
        return neoId;
    }
    public void setNeoId(Long neoId) {
        this.neoId = neoId;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCallsign() {
        return callsign;
    }
    public void setCallsign(String callsign) {
        this.callsign = callsign;
    }
    public String getSquawk() {
        return squawk;
    }
    public void setSquawk(String squawk) {
        this.squawk = squawk;
    }
    public String getReg() {
        return reg;
    }
    public void setReg(String reg) {
        this.reg = reg;
    }
    public String getFlightno() {
        return flightno;
    }
    public void setFlightno(String flightno) {
        this.flightno = flightno;
    }
    public String getRoute() {
        return route;
    }
    public void setRoute(String route) {
        this.route = route;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public int getAltitude() {
        return altitude;
    }
    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }
    public int getHeading() {
        return heading;
    }
    public void setHeading(int heading) {
        this.heading = heading;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getVertRate() {
        return vertRate;
    }
    public void setVertRate(int vertRate) {
        this.vertRate = vertRate;
    }
    public int getSelectedAltitude() {
        return selectedAltitude;
    }
    public void setSelectedAltitude(int selectedAltitude) {
        this.selectedAltitude = selectedAltitude;
    }
    public double getLat() {
        return lat;
    }
    public void setLat(double lat) {
        this.lat = lat;
    }
    public double getLon() {
        return lon;
    }
    public void setLon(double lon) {
        this.lon = lon;
    }
    public double getBarometer() {
        return barometer;
    }
    public void setBarometer(double barometer) {
        this.barometer = barometer;
    }
    public double getPolarDistance() {
        return polarDistance;
    }
    public void setPolarDistance(double polarDistance) {
        this.polarDistance = polarDistance;
    }
    public double getPolarBearing() {
        return polarBearing;
    }
    public void setPolarBearing(double polarBearing) {
        this.polarBearing = polarBearing;
    }
    public boolean isAdsb() {
        return isAdsb;
    }
    public void setAdsb(boolean adsb) {
        isAdsb = adsb;
    }
    public boolean isOnGround() {
        return isOnGround;
    }
    public void setOnGround(boolean onGround) {
        isOnGround = onGround;
    }
    public Instant getLastSeenTime() {
        return lastSeenTime;
    }
    public void setLastSeenTime(Instant lastSeenTime) {
        this.lastSeenTime = lastSeenTime;
    }
    public Instant getPosUpdateTime() {
        return posUpdateTime;
    }
    public void setPosUpdateTime(Instant posUpdateTime) {
        this.posUpdateTime = posUpdateTime;
    }
    public Instant getBds40SeenTime() {
        return bds40SeenTime;
    }
    public void setBds40SeenTime(Instant bds40SeenTime) {
        this.bds40SeenTime = bds40SeenTime;
    }

    @Override
    public String toString() {
        return "Aircraft{" +
                "neoId=" + neoId +
                ", id=" + id +
                ", callsign='" + callsign + '\'' +
                ", squawk='" + squawk + '\'' +
                ", reg='" + reg + '\'' +
                ", flightno='" + flightno + '\'' +
                ", route='" + route + '\'' +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                ", altitude=" + altitude +
                ", heading=" + heading +
                ", speed=" + speed +
                ", vertRate=" + vertRate +
                ", selectedAltitude=" + selectedAltitude +
                ", lat=" + lat +
                ", lon=" + lon +
                ", barometer=" + barometer +
                ", polarDistance=" + polarDistance +
                ", polarBearing=" + polarBearing +
                ", isAdsb=" + isAdsb +
                ", isOnGround=" + isOnGround +
                ", lastSeenTime=" + lastSeenTime +
                ", posUpdateTime=" + posUpdateTime +
                ", bds40SeenTime=" + bds40SeenTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Aircraft aircraft)) return false;
        return altitude == aircraft.altitude &&
                heading == aircraft.heading &&
                speed == aircraft.speed &&
                vertRate == aircraft.vertRate &&
                selectedAltitude == aircraft.selectedAltitude &&
                Double.compare(lat, aircraft.lat) == 0 &&
                Double.compare(lon, aircraft.lon) == 0 &&
                Double.compare(barometer, aircraft.barometer) == 0 &&
                Double.compare(polarDistance, aircraft.polarDistance) == 0 &&
                Double.compare(polarBearing, aircraft.polarBearing) == 0 &&
                isAdsb == aircraft.isAdsb &&
                isOnGround == aircraft.isOnGround &&
                Objects.equals(neoId, aircraft.neoId) &&
                Objects.equals(id, aircraft.id) &&
                Objects.equals(callsign, aircraft.callsign) &&
                Objects.equals(squawk, aircraft.squawk) &&
                Objects.equals(reg, aircraft.reg) &&
                Objects.equals(flightno, aircraft.flightno) &&
                Objects.equals(route, aircraft.route) &&
                Objects.equals(type, aircraft.type) &&
                Objects.equals(category, aircraft.category) &&
                Objects.equals(lastSeenTime, aircraft.lastSeenTime) &&
                Objects.equals(posUpdateTime, aircraft.posUpdateTime) &&
                Objects.equals(bds40SeenTime, aircraft.bds40SeenTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(neoId, id, callsign, squawk, reg, flightno, route,
                type, category, altitude, heading, speed, vertRate,
                selectedAltitude, lat, lon, barometer, polarDistance,
                polarBearing, isAdsb, isOnGround, lastSeenTime, posUpdateTime,
                bds40SeenTime);
    }
}
