package client.helper;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.imageio.ImageIO;

import java.awt.*;
import java.net.URL;

import static org.mockito.Mockito.*;

public class RowCountTest {

    @Mock
    GridPane mockPane;
    @Mock
    ObservableList<RowConstraints> mockConstraints;
    @Mock
    ObservableList<Node> mockNodes;
    @Mock
    Node mockNode;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        when(mockPane.getRowConstraints()).thenReturn(mockConstraints);
        when(mockConstraints.size()).thenReturn(5);

        when(mockPane.getChildren()).thenReturn(mockNodes);
        when(mockNodes.size()).thenReturn(6);
        when(mockNodes.get(anyInt())).thenReturn(mockNode);


        //when(mockPane.getRowIndex(mockNode)).thenReturn(4);
        //doReturn(4).when(mockPane.getRowIndex(any()));

    }

    @Test
    public void testManaged(){
        when(mockNode.isManaged()).thenReturn(true);
        Assert.assertEquals(5, RowCount.getRowCount(mockPane));
    }

    @Test
    public void testNotManaged(){
        when(mockNode.isManaged()).thenReturn(false);
        Assert.assertEquals(5, RowCount.getRowCount(mockPane));
    }
}
