/*
Freeware License, some rights reserved

Copyright (c) 2023 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy
of this software and associated documentation files (the "Software"),
to work with the Software within the limits of freeware distribution and fair use.
This includes the rights to use, copy, and modify the Software for personal use.
Users are also allowed and encouraged to submit corrections and modifications
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for
commercial use in any way, or for a user's educational materials such as books
or blog articles without prior permission from the copyright holder.

The above copyright notice and this permission notice need to be included
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.example.demo.Chapter9_TransactionH2.entities;

import jakarta.persistence.*;

import java.io.Serial;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 4/22/17.
 */
@Entity
@Table(name = "SINGER")
@NamedQueries({
		@NamedQuery(name=Singer.FIND_ALL, query="select s from Singer s"),
		@NamedQuery(name=Singer.FIND_SINGER_BY_ID,
				query="""
      				select distinct s from Singer s
     				left join fetch s.albums a
					left join fetch s.instruments i
					where s.id = :id
					"""),
		@NamedQuery(name=Singer.FIND_ALL_WITH_ALBUM,
				query="""
      				select distinct s from Singer s
					left join fetch s.albums a
					left join fetch s.instruments i
					""")
})
@SqlResultSetMapping(
		name="singerResult",
		entities=@EntityResult(entityClass=Singer.class)
)
@NamedNativeQueries({
		@NamedNativeQuery(
				name = "Singer.getFirstNameById(?)",
				query = "select getfirstnamebyid(?)")
})
@NamedStoredProcedureQuery(
		name = "getFirstNameByIdProc",
		procedureName = "getFirstNameByIdProc",
		parameters = {
				@StoredProcedureParameter(
						name = "in_id",
						type = Long.class,
						mode = ParameterMode.IN
				),
				@StoredProcedureParameter(
						name = "fn_res",
						type = String.class,
						mode = ParameterMode.OUT
				)
		}
)
public class Singer extends AbstractEntity {
	@Serial
	private static final long serialVersionUID = 2L;

	public static final String FIND_ALL = "Singer.findAll";
	public static final String FIND_SINGER_BY_ID = "Singer.findById";
	public static final String FIND_ALL_WITH_ALBUM = "Singer.findAllWithAlbum";

	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private Set<Album> albums = new HashSet<>();
	private Set<Instrument> instruments = new HashSet<>();

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return this.firstName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return this.lastName;
	}

	@Column(name = "BIRTH_DATE")
	public LocalDate getBirthDate() {
		return birthDate;
	}

	@OneToMany(mappedBy = "singer", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set<Album> getAlbums() {
		return albums;
	}

	@ManyToMany
	@JoinTable(name = "SINGER_INSTRUMENT",
			joinColumns = @JoinColumn(name = "SINGER_ID"),
			inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
	public Set<Instrument> getInstruments() {
		return instruments;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean addAlbum(Album album) {
		album.setSinger(this);
		return getAlbums().add(album);
	}

	public void removeAlbum(Album album) {
		getAlbums().remove(album);
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setInstruments(Set<Instrument> instruments) {
		this.instruments = instruments;
	}

	public boolean addInstrument(Instrument instrument) {
		return getInstruments().add(instrument);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Singer singer = (Singer) o;
		if(this.id != null) {
			return this.id.equals(((Singer) o).id);
		}
		return firstName.equals(singer.firstName) && lastName.equals(singer.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

	public String toString() {
		return "Singer - Id: " + id + ", First name: " + firstName
				+ ", Last name: " + lastName + ", Birthday: " + birthDate;
	}
}
