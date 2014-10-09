package io.tracee.cxf.client;

import io.tracee.SimpleTraceeBackend;
import io.tracee.TraceeBackend;
import io.tracee.TraceeConstants;
import io.tracee.cxf.interceptor.TraceeOutInterceptor;
import io.tracee.transport.HttpJsonHeaderTransport;
import io.tracee.transport.TransportSerialization;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class OutgoingMessageTest {

	private static final TraceeBackend backend = SimpleTraceeBackend.createNonLoggingAllPermittingBackend();

	private Interceptor<Message> OUT;

	private final MessageImpl message = new MessageImpl();

	private TransportSerialization<String> httpSerializer;

	@Before
	public void onSetup() throws Exception {
		OUT = new TraceeOutInterceptor(backend);
		httpSerializer = new HttpJsonHeaderTransport(backend.getLoggerFactory());
	}

	@Test
	public void renderEmptyContextToResponse() {
		OUT.handleMessage(message);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void renderContextToResponse() {
		backend.put("myContextKey", "contextValue2");
		OUT.handleMessage(message);
		final Map<Object, Object> headers = CastUtils.cast((Map<?, ?>) message.get(Message.PROTOCOL_HEADERS));
		final Map<String, String> traceeContext = httpSerializer.parse(((List<String>) headers.get(TraceeConstants.HTTP_HEADER_NAME)).get(0));
		assertThat(traceeContext.get("myContextKey"), is("contextValue2"));
	}
}
