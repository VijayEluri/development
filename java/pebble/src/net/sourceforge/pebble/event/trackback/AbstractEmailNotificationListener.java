/*
 * Copyright (c) 2003-2006, Simon Brown
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in
 *     the documentation and/or other materials provided with the
 *     distribution.
 *
 *   - Neither the name of Pebble nor the names of its contributors may
 *     be used to endorse or promote products derived from this software
 *     without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package net.sourceforge.pebble.event.trackback;

import java.text.SimpleDateFormat;
import java.util.Collection;

import net.sourceforge.pebble.api.event.trackback.TrackBackEvent;
import net.sourceforge.pebble.domain.Blog;
import net.sourceforge.pebble.domain.TrackBack;
import net.sourceforge.pebble.util.MailUtils;

/**
 * Base class for listeners that send an e-mail notification when new
 * TrackBacks are added.
 *
 * @author Simon Brown
 */
public abstract class AbstractEmailNotificationListener extends TrackBackListenerSupport {

  /**
   * Called when a TrackBack has been added.
   *
   * @param event a TrackBackEvent instance
   */
  public void trackBackAdded(TrackBackEvent event) {
    sendNotification(event.getTrackBack());
  }

  private void sendNotification(TrackBack trackBack) {
    Blog blog = trackBack.getBlogEntry().getBlog();

    SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy HH:mm:ss z");
    sdf.setTimeZone(blog.getTimeZone());

    String subject = "[TrackBack-" + trackBack.getState().getName() + "] " + trackBack.getTitle();

    String message = "TrackBack from <a href=\"" + trackBack.getUrl() + "\">" + trackBack.getBlogName() + "</a> on " + sdf.format(trackBack.getDate());
    message += " in response to " + trackBack.getBlogEntry().getTitle();
    message += "\n\n<br><br>";
    message += trackBack.getExcerpt();
    message += "\n\n<br><br>";
    message += "<a href=\"" + trackBack.getPermalink() + "\">Permalink</a>";

    if (trackBack.isPending()) {
      message += " | ";
      message += "<a href=\"" + blog.getUrl() + "manageResponses.secureaction?response=" + trackBack.getGuid() + "&submit=Approve" + "\">Approve</a>";
      message += " | ";
      message += "<a href=\"" + blog.getUrl() + "manageResponses.secureaction?response=" + trackBack.getGuid() + "&submit=Reject" + "\">Reject</a>";
    }

    message += " | ";
    message += "<a href=\"" + blog.getUrl() + "manageResponses.secureaction?response=" + trackBack.getGuid() + "&submit=Remove" + "\">Remove</a>";

    Collection to = getEmailAddresses(trackBack);

    try {
      MailUtils.sendMail(MailUtils.createSession(), blog, to, subject, message);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Returns the collection of recipients.
   *
   * @param trackBack   the TrackBack from the event
   * @return  a Collection of e-mail addresses (Strings)
   */
  protected abstract Collection getEmailAddresses(TrackBack trackBack);

}
