package com.rijai.BookingAPI.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String resname;
    private String date;
    private String time;
    private int guestcount;
    private String reservationtype;
    private String vouchercode;
    private String status;
    private String canreason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Booking() {
    }

    public Booking(long id, String resname, String date, String time, int guestcount, String reservationtype, String vouchercode, String status, String canreason) {
        this.id = id;
        this.resname = resname;
        this.date = date;
        this.time = time;
        this.guestcount = guestcount;
        this.reservationtype = reservationtype;
        this.vouchercode = vouchercode;
        this.status = status;
        this.canreason = canreason;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getResname() {
        return resname;
    }

    public void setResname(String resname) {
        this.resname = resname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getGuestcount() {
        return guestcount;
    }

    public void setGuestcount(int guestcount) {
        this.guestcount = guestcount;
    }

    public String getReservationtype() {
        return reservationtype;
    }

    public void setReservationtype(String reservationtype) {
        this.reservationtype = reservationtype;
    }

    public String getVouchercode() {
        return vouchercode;
    }

    public void setVouchercode(String vouchercode) {
        this.vouchercode = vouchercode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCanreason() {
        return canreason;
    }

    public void setCanreason(String canreason) {
        this.canreason = canreason;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.resname);
        hash = 79 * hash + Objects.hashCode(this.date);
        hash = 79 * hash + Objects.hashCode(this.time);
        hash = 79 * hash + Objects.hashCode(this.guestcount);
        hash = 79 * hash + Objects.hashCode(this.reservationtype);
        hash = 79 * hash + Objects.hashCode(this.vouchercode);
        hash = 79 * hash + Objects.hashCode(this.status);
        hash = 79 * hash + Objects.hashCode(this.canreason);
        hash = 79 * hash + Objects.hashCode(this.user.getName());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Booking other = (Booking) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.canreason, other.canreason)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.vouchercode, other.vouchercode)) {
            return false;
        }
        if (!Objects.equals(this.reservationtype, other.reservationtype)) {
            return false;
        }
        if (!Objects.equals(this.guestcount, other.guestcount)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.resname, other.resname)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Booking{");
        sb.append("id=").append(id);
        sb.append(", resname='").append(resname).append('\'');
        sb.append(", date='").append(date).append('\'');
        sb.append(", time='").append(time).append('\'');
        sb.append(", guestcount=").append(guestcount);
        sb.append(", reservationtype='").append(reservationtype).append('\'');
        sb.append(", vouchercode='").append(vouchercode).append('\'');
        sb.append(", reservationtype='").append(status).append('\'');
        sb.append(", vouchercode='").append(canreason).append('\'');
        sb.append(", user=").append(user.getName());
        sb.append('}');
        return sb.toString();
    }
}