package com.thehecklers.planefinder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data   // Getter, Setter, toString, hashcode, equals
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Aircraft {
    @Id
    @GeneratedValue
    private Long id;

    private String callsign, squawk, reg, flightno, route, type, category;

    private int altitude, heading, speed;

    @JsonProperty("vert_rate")  // vert_rate 키 값을 vrertRate 필드에 대입
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

    // 부분 필드 값을 이용한 생성자
    public Aircraft(String callsign, String reg, String flightno, String type,
                    int altitude, int heading, int speed, double lat, double lon) {
        this(null, callsign, "sqwk", reg,  flightno, "route", type, "cat", altitude, heading, speed, 0, 0,
                lat, lon, 0D, 0D, 0D, false, true,
                Instant.now(), Instant.now(), Instant.now());

    }

    public void setLastSeenTime(Long lastSeenTime) {
        this.lastSeenTime = Instant.ofEpochSecond(lastSeenTime);
    }

    public void setPosUpdateTime(Long posUpdateTime) {
        this.posUpdateTime = Instant.ofEpochSecond(posUpdateTime);
    }

    public void setBds40SeenTime(Long bds40SeenTime) {
        this.bds40SeenTime = Instant.ofEpochSecond(bds40SeenTime);
    }
}
