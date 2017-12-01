
package gymsym;

/**
 *
 * @author DoctorP
 */
class RoomAlreadyInDatabase extends Exception {
    RoomAlreadyInDatabase(String err){
        super(err);
    }
}
