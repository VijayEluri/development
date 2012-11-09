package net.sourceforge.pebble.domain;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import net.sourceforge.pebble.PebbleContext;
import net.sourceforge.pebble.security.PebbleUserDetails;
import net.sourceforge.pebble.security.SecurityRealm;
import net.sourceforge.pebble.security.SecurityRealmException;
import net.sourceforge.pebble.util.StringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The superclass for blog entries and pages.
 *
 * @author Simon Brown
 */
public abstract class PageBasedContent extends Content {

  private static final Log log = LogFactory.getLog(PageBasedContent.class);

  public static final String TITLE_PROPERTY = "title";
  public static final String SUBTITLE_PROPERTY = "subtitle";
  public static final String BODY_PROPERTY = "body";
  public static final String AUTHOR_PROPERTY = "author";
  public static final String DATE_PROPERTY = "date";
  public static final String ORIGINAL_PERMALINK_PROPERTY = "originalPermalink";
  public static final String TAGS_PROPERTY = "tags";

  /**
   * the id
   */
  private String id;

  /**
   * the title
   */
  private String title = "";

  /**
   * the subtitle
   */
  private String subtitle = "";

  /**
   * the body
   */
  private String body = "";

  /**
   * the date that the content was created
   */
  private Date date;

  /**
   * the author of the blog entry
   */
  private String author = "";

  /** the enriched user details */
  private PebbleUserDetails user;

  /**
   * the alternative permalink for this blog entry
   */
  private String originalPermalink;

  /** the list of tags for this blog entry */
  private String tags = "";

  /** the List of tags for this blog entry */
  private List<Tag> tagsAsList = new LinkedList<Tag>();

  /** the tags, comma separated */
  private String tagsAsCommaSeparated = "";

  /** the owning blog */
  private Blog blog;

  private boolean persistent = false;
  private String lockedBy = null;

  /**
   * Creates a new blog entry.
   *
   * @param blog    the owning Blog
   */
  public PageBasedContent(Blog blog) {
    this.blog = blog;
    setDate(new Date());
  }

  /**
   * Gets the unique id of this blog entry.
   *
   * @return the id as a String
   */
  public String getId() {
    return id;
  }

  /**
   * Gets the title of this blog entry.
   *
   * @return the title as a String
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title of this blog entry.
   *
   * @param newTitle  the title as a String
   */
  public void setTitle(String newTitle) {
	newTitle = newTitle;
    propertyChangeSupport.firePropertyChange(TITLE_PROPERTY, title, newTitle);
    this.title = newTitle;
  }

  /**
   * Gets the subtitle of this blog entry.
   *
   * @return the subtitle as a String
   */
  public String getSubtitle() {
    return subtitle;
  }

  /**
   * Sets the subtitle of this blog entry.
   *
   * @param newSubtitle  the subtitle as a String
   */
  public void setSubtitle(String newSubtitle) {
	//newSubtitle = StringUtils.transformHTML(newSubtitle);
    propertyChangeSupport.firePropertyChange(SUBTITLE_PROPERTY, subtitle, newSubtitle);
    this.subtitle = newSubtitle;
  }

  /**
   * Gets the body of this blog entry.
   *
   * @return the body as a String
   */
  public String getBody() {
    return body;
  }

  /**
   * Gets the content of this response.
   *
   * @return a String
   */
  public String getContent() {
    return body;
  }

  /**
   * Sets the body of this blog entry.
   *
   * @param newBody the body as a String
   */
  public void setBody(String newBody) {
    propertyChangeSupport.firePropertyChange(BODY_PROPERTY, body, newBody);
    this.body = newBody;
  }

  /**
   * Gets the date that this blog entry was created.
   *
   * @return a java.util.Date instance
   */
  public Date getDate() {
    return date;
  }

  /**
   * Gets the date that this blog entry was last updated.
   *
   * @return  a Date instance representing the time of the last comment/TrackBack
   */
  public Date getLastModified() {
    return date;
  }

  /**
   * Sets the date that this blog entry was created.
   *
   * @param newDate a java.util.Date instance
   */
  public void setDate(Date newDate) {
    propertyChangeSupport.firePropertyChange(DATE_PROPERTY, date, newDate);
    this.date = newDate;
    this.id = "" + this.date.getTime();
  }

  /**
   * Gets the author of this blog entry.
   *
   * @return the author as a String
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Gets full user details about the author including name, email-address, etc.
   *
   * @return  a PebbleUserDetails instance
   */
  public PebbleUserDetails getUser() {
    if (this.user == null) {
      SecurityRealm realm = PebbleContext.getInstance().getConfiguration().getSecurityRealm();
      try {
        this.user = realm.getUser(getAuthor());
      } catch (SecurityRealmException e) {
        log.error(e);
      }
    }

    return this.user;
  }

  /**
   * Sets the author of this blog entry.
   *
   * @param newAuthor the author as a String
   */
  public void setAuthor(String newAuthor) {
    this.author = StringUtils.transformHTML(newAuthor);
  }

  /**
   * Gets the tags associated with this category.
   *
   * @return  a list of tags
   */
  public String getTags() {
    return this.tags;
  }

  /**
   * Gets the tags associated with this category, as a List.
   *
   * @return  a List of tags
   */
  public List<Tag> getTagsAsList() {
    return this.tagsAsList;
  }

  /**
   * Gets a list of all tags, as a comma separated string.
   *
   * @return    a comma separated String of tags
   */
  public String getTagsAsCommaSeparated() {
    return this.tagsAsCommaSeparated;
  }

  /**
   * Gets a list of all tags.
   *
   * @return  a List of tags
   */
  public abstract List<Tag> getAllTags();

  /**
   * Sets the set of tags associated with this category.
   *
   * @param newTags    a set of tags
   */
  public void setTags(String newTags) {
    if (newTags != null && newTags.indexOf(",") > -1) {
      // if the tags have been comma separated, convert them to
      // whitespace separated by
      // - remove whitespace
      // - convert commas to whitespace
      newTags = newTags.replaceAll(" ", "").replaceAll(",", " ");
    }
    propertyChangeSupport.firePropertyChange(TAGS_PROPERTY, tags, newTags);
    this.tags = newTags;
    this.tagsAsList = Tag.parse(getBlog(), tags);
    this.tagsAsCommaSeparated = Tag.format(getAllTags());
  }

  /**
   * Determines whether this blog entry has been aggregated from another
   * source. An aggregated blog entry will have a specified permalink.
   *
   * @return true if this blog entry has been aggegrated, false otherwise
   */
  public boolean isAggregated() {
    return (originalPermalink != null);
  }

  /**
   * Gets the alternative permalink for this blog entry.
   *
   * @return an absolute URL as a String
   */
  public String getOriginalPermalink() {
    return this.originalPermalink;
  }

  /**
   * Sets the alternative permalink for this blog entry.
   *
   * @param newPermalink an absolute URL as a String
   */
  public void setOriginalPermalink(String newPermalink) {
	newPermalink = StringUtils.transformHTML(newPermalink);
    if (newPermalink == null || newPermalink.length() == 0) {
      propertyChangeSupport.firePropertyChange(ORIGINAL_PERMALINK_PROPERTY, originalPermalink, null);
      this.originalPermalink = null;
    } else {
      propertyChangeSupport.firePropertyChange(ORIGINAL_PERMALINK_PROPERTY, originalPermalink, newPermalink);
      this.originalPermalink = newPermalink;
    }
  }

  /**
   * Gets a permalink for this blog entry.
   *
   * @return an absolute URL as a String
   */
  public String getPermalink() {
    if (isAggregated()) {
      return getOriginalPermalink();
    } else {
      return getLocalPermalink();
    }
  }

  /**
   * Gets a permalink for this blog entry that is local to the blog. In other
   * words, it doesn't take into account the original permalink for
   * aggregated content.
   *
   * @return an absolute URL as a String
   */
  public abstract String getLocalPermalink();

  /**
   * Helper method to get the owning Blog instance.
   *
   * @return the overall owning Blog instance
   */
  public Blog getBlog() {
    return this.blog;
  }

  /**
   * Gets a string representation of this object.
   *
   * @return  a String
   */
  public String toString() {
    return getBlog().getId() + "/" + getTitle();
  }

  public boolean isPersistent() {
    return persistent;
  }

  public void setPersistent(boolean persistent) {
    this.persistent = persistent;
  }

  /**
   * Determines whether this content is published.
   *
   * @return  true if the state is published, false otherwise
   */
  public boolean isPublished() {
    return getState().equals(State.PUBLISHED);
  }

  /**
   * Determines whether this content is unpublished.
   *
   * @return  true if the state is unpublished, false otherwise
   */
  public boolean isUnpublished() {
    return getState().equals(State.UNPUBLISHED);
  }

  /**
   * Sets the state of this content.
   *
   * @param published   true if this content is published, false if unpublished
   */
  public void setPublished(boolean published) {
    if (published) {
      setState(State.PUBLISHED);
    } else {
      setState(State.UNPUBLISHED);
    }
  }

  public String getLockedBy() {
    return lockedBy;
  }

  public void setLockedBy(String lockedBy) {
    this.lockedBy = lockedBy;
  }

}