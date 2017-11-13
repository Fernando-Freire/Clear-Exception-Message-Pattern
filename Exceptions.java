import java.net.*;
import java.io.*;

public class Exceptions {
  public static void main(String[] args) throws Exception {
    System.out.println();
    try {
      openURL("http://www.google.br");
    } catch (PageNotFoundException e) {
      e.printStackTrace();
    }
    System.out.println();
    try {
      openURL("dashboard.heroku.com");
    } catch (ForbiddenAccessException e) {
      e.printStackTrace();
    }
    System.out.println();
    try {
      multiplyMatrix(new int[1][3], new int[2][1]);
    } catch (WrongDimensionsException e) {
      e.printStackTrace();
    }
    System.out.println();
    int[][] A = {{4, 5}, {1, 2}};
    int[][] B = {{1, 5}, {-1, 4}};
    try {
      multiplyMatrix(A, B);
    } catch (NegativeValuesInMatrixException e) {
      e.printStackTrace();
    }
    System.out.println();
  }

  private static void openURL(String urlToRead) throws ConnectionException {
    if (urlToRead == "http://www.google.br") {
      throw new PageNotFoundException("Are you sure you typed in the right address?", "http://www.google.br");
    } else if (urlToRead == "dashboard.heroku.com") {
      throw new ForbiddenAccessException("You need to login as an admin first.", "dashboard.heroku.com");
    }
  }

  private static void multiplyMatrix(int[][] A, int[][] B) throws MatrixMultiplicationException {
    if (A[0].length != B.length) {
      throw new WrongDimensionsException("A (" + A.length + ", " + A[0].length + ") x B (" + B.length + ", " + B[0].length + ")");
    }

    for (int i = 0; i < A.length; i++)
      for (int x : A[i]) 
        if (x < 0) throw new NegativeValuesInMatrixException("");

    for (int i = 0; i < B.length; i++)
      for (int x : B[i]) 
        if (x < 0) throw new NegativeValuesInMatrixException("");
  }
}

class ConnectionException extends Exception {
  public ConnectionException(String message, Integer status) {
    super("\n\tConnection Error - status code " + status + "\n" + message);
  }
}

class PageNotFoundException extends ConnectionException {
  public PageNotFoundException(String message, String page) {
    super("\tCouldn't find the page " + page + ". " + message, 404);
  }
}

class ForbiddenAccessException extends ConnectionException {
  public ForbiddenAccessException(String message, String page) {
    super("\tYou don't have access to the page " + page + ". " + message, 403);
  }
}

class UserException extends Exception {
  public UserException(String message, String feature, String link) {
    super("\n\tInput Error - for more info on how to use " + feature + " please check " + link + "\n" + message);
  }
}

class MatrixMultiplicationException extends UserException {
  public MatrixMultiplicationException(String message) {
    super("\tMatrix Multiplication Error \n" + message, "Matrix Multiplication", "https://en.wikipedia.org/wiki/Matrix_multiplication");
  }
}

class WrongDimensionsException extends MatrixMultiplicationException {
  public WrongDimensionsException(String message) {
    super("\tThese matrices are not the same size! " + message);
  }
}

class NegativeValuesInMatrixException extends MatrixMultiplicationException {
  public NegativeValuesInMatrixException(String message) {
    super("\tI don't know how to multiply matrices with negative values... " + message);
  }
}

