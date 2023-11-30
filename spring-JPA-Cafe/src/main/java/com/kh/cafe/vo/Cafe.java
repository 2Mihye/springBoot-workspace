package com.kh.cafe.vo;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Table(name = "Cafe")
@SequenceGenerator(name = "cafe_seqence", sequenceName = "cafe_sequence", allocationSize = 1)
public class Cafe {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="cafe_sequence")
	@Column(name="cafe_id")
	private Long cafeID;
	@Column(name="cafe_name")
	private String cafeName;
	@Column(name="location")
	private String location;
	@Column(name="opening_hours")
	private String openingHours;
}
