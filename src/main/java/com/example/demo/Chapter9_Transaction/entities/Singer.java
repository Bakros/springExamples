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
package com.example.demo.Chapter9_Transaction.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

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
		@NamedQuery(name=Singer.COUNT_ALL, query="select count(s) from Singer s"),
		@NamedQuery(name=Singer.FIND_BY_FIRST_AND_LAST_NAME, query="select s from Singer s where s.firstName = :fn and s.lastName = :ln")
})
public class Singer extends AbstractEntity {
	@Serial
	private static final long serialVersionUID = 2L;

	public static final String FIND_ALL = "Singer.findAll";
	public static final String COUNT_ALL = "Singer.countAll";
	public static final String FIND_BY_FIRST_AND_LAST_NAME = "Singer.findByFirstAndLastName";

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "BIRTH_DATE")
	private LocalDate birthDate;
	@OneToMany(mappedBy = "singer")
	private Set<Album> albums = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "SINGER_INSTRUMENT",
			joinColumns = @JoinColumn(name = "SINGER_ID"),
			inverseJoinColumns = @JoinColumn(name = "INSTRUMENT_ID"))
	private Set<Instrument> instruments = new HashSet<>();

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

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
