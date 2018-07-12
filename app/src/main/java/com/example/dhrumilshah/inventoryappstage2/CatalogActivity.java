package com.example.dhrumilshah.inventoryappstage2;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.dhrumilshah.inventoryappstage2.data.BooksBoxContract.BooksBoxEntry;

public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    /** Identifier for the book data loader */
    private static final int BOOK_LOADER = 0;

    RelativeLayout emptyView;

    /** Adapter for the ListView */
    BooksCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        FloatingActionButton fab = findViewById(R.id.insert_book_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // Find the ListView which will be populated with the book data
        ListView booksListView = findViewById(R.id.list_view_books);

        // Find and set empty view on the ListView, so that it only shows when the list has 0 items.
        emptyView = findViewById(R.id.empty_view);
        booksListView.setEmptyView(emptyView);


        // Setup an Adapter to create a list item for each row of book data in the Cursor.
        // There is no book data yet (until the loader finishes) so pass in null for the Cursor.
        adapter = new BooksCursorAdapter(this, null);
        booksListView.setAdapter(adapter);

        // Setup the item click listener
        booksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Create new intent to go to {@link EditorActivity}
                Intent i = new Intent(CatalogActivity.this, EditorActivity.class);
                // Form the content URI that represents the specific book that was clicked on,
                // by appending the "id" (passed as input to this method) onto the
                // {@link BooksBoxEntry#CONTENT_URI}.
                // For example, the URI would be "content://com.example.dhrumilshah.inventoryappstage2/books/2"
                // if the book with ID 2 was clicked on.
                Uri currentBookUri = ContentUris.withAppendedId(BooksBoxEntry.CONTENT_URI, id);
                i.setData(currentBookUri);
                startActivity(i);
            }
        });


        getLoaderManager().initLoader(BOOK_LOADER, null, this);
    }

    private void deleteAllBooks() {
        // Defines a variable to contain the number of rows deleted
        int rowsDeleted = 0;

        // Deletes the rows that match the selection criteria
        rowsDeleted = getContentResolver().delete(
                BooksBoxEntry.CONTENT_URI,   // the user dictionary content URI
                null,                    // the column to select on
                null                      // the value to compare to
        );
        if (rowsDeleted == 0) {
            // If the value of rowsDeleted is 0, then there was problem with deleting rows
            // or no rows match the selection criteria.
            Toast.makeText(this, R.string.error_while_deleting_books,
                    Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the deletion was successful and we can display a toast.
            Toast.makeText(this, R.string.all_books_deleted,
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void showDeleteConfirmationDialog() {

        /*
         * If emptyView is already visible, then it means there are no entries in the table.
         * Thus we don't need to show dialog box to the user for deleting all the entries in the table as table is already empty.
         */
        if(!(emptyView.getVisibility() == View.VISIBLE)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.delete_all_books);
            builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked the "Delete" button, so delete the book.
                    deleteAllBooks();
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked the "Cancel" button, so dismiss the dialog
                    // and continue editing the book.
                    if (dialog != null) {
                        dialog.dismiss();
                    }
                }
            });

            // Create and show the AlertDialog
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()){
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                showDeleteConfirmationDialog();
                return true;
            default:
                return false;
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Define a projection that specifies the columns from the table we care about.
        String[] projection = {
                BooksBoxEntry._ID,
                BooksBoxEntry.COLUMN_PRODUCT_NAME,
                BooksBoxEntry.COLUMN_PRODUCT_PRICE,
                BooksBoxEntry.COLUMN_PRODUCT_QUANTITY,
                BooksBoxEntry.COLUMN_SUPPLIER_NAME,
                BooksBoxEntry.COLUMN_SUPPLIER_PHONE_NUMBER,
        };
        // This loader will execute the ContentProvider's query method on a background thread
        return  new CursorLoader(this,
                BooksBoxEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Update {@link BooksCursorAdapter} with this new cursor containing updated book data
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // Callback called when the data needs to be deleted
        adapter.swapCursor(null);
    }
}
