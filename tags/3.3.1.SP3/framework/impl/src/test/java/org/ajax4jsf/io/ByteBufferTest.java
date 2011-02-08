/* 
 * JBoss, Home of Professional Open Source 
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved. 
 * See the copyright.txt in the distribution for a 
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use, 
 * modify, copy, or redistribute it subject to the terms and conditions 
 * of the GNU Lesser General Public License, v. 2.1. 
 * This program is distributed in the hope that it will be useful, but WITHOUT A 
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A 
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details. 
 * You should have received a copy of the GNU Lesser General Public License, 
 * v.2.1 along with this distribution; if not, write to the Free Software 
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 */
package org.ajax4jsf.io;

import junit.framework.TestCase;

/**
 * Test cases for the ByteBuffer class
 * @author balunasj
 *
 */
public class ByteBufferTest extends TestCase {
	
	/**
	 * Test method for RF-8738 at {@link org.ajax4jsf.io.ByteBuffer#append(byte[] bs, int off, int len)}.
	 * 
	 * RF-8738 reports a possible endless recursion when appending to a byte buffer
	 * who is not the last in the chain.  i.e. who's "next" is not null.
	 */
	public void testByteBufferAppend_RF_8738() throws Exception {
		//Does not really matter how big this is for the test
		int cacheSize = 2;
		
		//Create two buffer
		ByteBuffer	buffer1 = new ByteBuffer(cacheSize);
		ByteBuffer	buffer2 = new ByteBuffer(cacheSize);
		
		//add one buffer as the "next" of the other
		buffer1.setNext(buffer2);
		
		//create a dummy byte array
		byte[] bs = new byte[10];
		
		try{
			//attempt to append the byte[] to the first buffer 
			buffer1.append(bs, 0, bs.length);
		}catch(StackOverflowError e){
			fail("StackOverflowError while attempting to test ByteBuffer.append(), " +
				  "endless recursion found");
		}
		
	}

}
