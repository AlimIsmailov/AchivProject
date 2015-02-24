package com.ita.softserveinc.achiever.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Objects;

/**
 * 
 * @author Ostap Palianytsia
 * 
 */
@NamedQueries (value= {		
		@NamedQuery(name = "Article.findBySubtopic", query = "SELECT a FROM Article a WHERE a.subtopic = :subtopic"),
		@NamedQuery(name = "Article.getByTitle", query = "SELECT a FROM Article a WHERE a.title = :title")
})
@Entity
@Table(name = "ARTICLES")
public class Article extends BaseEntity {

	private static final long serialVersionUID = 8640160242790685314L;

	@Column(name="TITLE", nullable = false)
	private String title;

	@Column(name ="DESCRIPTION")
	private String description;

	@Column(name = "URL", nullable = false)
	private String url;
	
	@Transient
	private MultipartFile file;
	
	@ManyToOne
	@JoinColumn(name = "SUBTOPIC_ID")
	private Subtopic subtopic;
	
	public Article() {
		super();
	}

	public Article(String title, String description, String url,
			Subtopic subtopic) {
		super();
		this.title = title;
		this.description = description;
		this.url = url;
		this.subtopic = subtopic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Subtopic getSubtopic() {
		return subtopic;
	}

	public void setSubtopic(Subtopic subtopic) {
		this.subtopic = subtopic;
	}
	
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.title, this.url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		final Article other = (Article) obj;
		return Objects.equal(this.title, other.title);
	}

	@Override
	public String toString() {

		return Objects.toStringHelper(this).add("title", this.title)
				.add("description", this.description)
				.add("url", this.url)
				.add("subtopic", this.subtopic.getName()).toString();

	}

}
