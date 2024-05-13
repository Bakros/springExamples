package com.example.HTMLTableDemo;

import com.example.demo.Chapter2.MessageProvider;
import com.example.demo.Chapter2.MessageRenderer;
import com.example.demo.Chapter2.StandardOutMessageRenderer;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


public class MessageRenderTest {

    @Test
    void testStandardOutMessageRenderer(){
        MessageProvider mockProvider =  mock(MessageProvider.class);
        when(mockProvider.getMessage()).thenReturn("test message");

        MessageRenderer messageRenderer = new StandardOutMessageRenderer();
        messageRenderer.setMessageProvider(mockProvider);

        messageRenderer.render();
        verify(mockProvider, times(1)).getMessage();
    }
}
