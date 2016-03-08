package org.monarchinitiative.ppk.model.packet;

import java.util.List;

import org.monarchinitiative.ppk.model.meta.Association;

@Deprecated
public class Profile<T extends Association> {
	List<T> profileMembers;
}
