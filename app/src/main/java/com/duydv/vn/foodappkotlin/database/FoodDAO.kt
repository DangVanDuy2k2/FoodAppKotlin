import androidx.room.*
import com.duydv.vn.foodappkotlin.model.Food
@Dao
interface FoodDAO {

    @Insert
    fun insertFood(food : Food)

    @Update
    fun updateFood(food: Food)

    @Delete
    fun deleteFood(food: Food)

    @Query("SELECT * FROM Food")
    fun getListFood() : List<Food>

    @Query("DELETE FROM Food")
    fun deleteAll()
}