# Lab 3 – Room Customer Database (Kotlin + Jetpack Compose)

Amran Hawaga · 101563789 · COMP3074

Room + SQLite app with one table, `customers` (`id`, `name`, `age`, `isActive`).
Customers are shown in a `LazyColumn` and can be added and deleted. Includes the search challenge.

## Structure
```
com.example.roomcustomers/
├── MainActivity.kt          gets the database, creates the ViewModel, sets the Compose UI
├── data/
│   ├── Customer.kt          @Entity(tableName = "customers")
│   ├── CustomerDao.kt       @Insert, @Delete, @Query (returns Flow)
│   └── AppDatabase.kt       RoomDatabase singleton ("customer_database")
└── ui/
    ├── CustomerViewModel.kt add / delete / search + factory
    └── CustomerScreen.kt    form, search bar, LazyColumn list, row cards
```

## Flow of data
Compose UI → CustomerViewModel → CustomerDao → AppDatabase (Room) → SQLite.
The DAO returns `Flow<List<Customer>>`, so when a row is inserted or deleted Room emits a new list and the UI recomposes. No "View All" button needed.

## Reflection answers (interview prep)
- **Room vs SQLite:** Room is an abstraction layer over SQLite. The data is still in a SQLite file.
- **@Entity:** marks a class as a table; each object is one row. `@PrimaryKey(autoGenerate = true)` makes `id` auto-increment.
- **DAO:** defines the allowed database operations. Room generates the implementation.
- **Why `suspend` for insert/delete:** database writes can't run on the main thread, so they run in a coroutine (`viewModelScope.launch`).
- **Why SELECT can return Flow:** Room watches the table and re-runs the query when it changes, emitting a new list.
- **Why no Cursor:** Room maps rows to `Customer` objects itself.
- **Why no View All button:** the UI observes the Flow with `collectAsStateWithLifecycle()`, so it updates automatically.
- **ViewModel's job:** holds UI data across rotation and launches DB work. The composables only draw the screen.
- **Room advantages:** compile-time SQL checks, less boilerplate, Boolean mapping, Flow/coroutine support.
- **What SQLiteOpenHelper taught that Room hides:** CREATE TABLE SQL, ContentValues, Cursor parsing, manual refresh.

## Test steps
1. Launch: shows "No customers in the database."
2. Add a few customers: each appears instantly with an auto-generated ID.
3. Leave the name or age empty: shows "Enter a valid name and age."
4. Delete one: the row disappears.
5. Close and reopen the app: the data is still there.
6. Search a name fragment, then Clear.
