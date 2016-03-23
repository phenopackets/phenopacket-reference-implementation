package org.phenopackets.api.model.evidence;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Jules Jacobsen <jules.jacobsen@sanger.ac.uk>
 */
public class PublicationTest {

    private static String ID = "ID:1";
    private static String TITLE  = "Title";
    private final Publication publication = new Publication.Builder().setId(ID).setTitle(TITLE).build();

    @Test
    public void testId() {
        assertThat(publication.getId(), equalTo(ID));
    }

    @Test
    public void testTitle() {
        assertThat(publication.getTitle(), equalTo(TITLE));
    }

    @Test
    public void testEquals() {
        Publication other = new Publication.Builder().setId(ID).setTitle(TITLE).build();
        assertThat(publication, equalTo(other));
    }

    @Test
    public void testNotEquals() {
        Publication otherId = new Publication.Builder().setId("ID:2").setTitle(TITLE).build();
        assertThat(publication, not(equalTo(otherId)));

        Publication otherTitle = new Publication.Builder().setId(ID).setTitle("wibble").build();
        assertThat(publication, not(equalTo(otherTitle)));
    }

    @Test
    public void testToString() {
        assertThat(publication.toString(), startsWith("Publication"));
    }
}