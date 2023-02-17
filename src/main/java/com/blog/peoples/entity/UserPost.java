package com.blog.peoples.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "user", "category" })
@Accessors(chain = true)
@Table(name = "p_u_post")
public class UserPost {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long postId;

	private String title;

	private String content;

	private String imageName;

	private Timestamp createDate;

	@ManyToOne
	private PeoplesUser user;

	@ManyToOne
	private UserCategory category;
}
